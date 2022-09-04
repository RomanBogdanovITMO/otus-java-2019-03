package java.otus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import otus.cache.CacheEngine;
import otus.cache.CacheEngineImpl;
import otus.dataset.AddressDataSet;
import otus.dataset.PhoneDataSet;
import otus.dataset.UserDataSet;
import otus.db.DBService;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CacheDBServiceImpl {
    private CacheEngine<Long,UserDataSet> cache;
    private DBService dbService;

    @Before
    public void init() {
        cache = new CacheEngineImpl.Builder<Long, UserDataSet>()
                .maxElements(5)
                .lifeTimeMS(500)
                .idleTimeMS(0)
                .isEternal(false)
                .build();
        dbService = new otus.db.CacheDBServiceImpl(cache);
    }

    @After
    public void shutDown() throws Exception {
        System.out.println("Successful");
    }

    @Test
    public void shouldIncrementHit() {
        PhoneDataSet phoneDataSet = PhoneDataSet.builder()
                .number("911")
                .build();
        AddressDataSet addressDataSet = AddressDataSet.builder()
                .street("address")
                .build();
        UserDataSet userDataSet = UserDataSet.builder()
                .name("roman")
                .age(10)
                .build();

        phoneDataSet.setUser(userDataSet);
        addressDataSet.setUserDataSet(userDataSet);
        userDataSet.setPhoneDataSetList(Collections.singletonList(phoneDataSet));
        userDataSet.setAddress(addressDataSet);

        dbService.create(userDataSet);

        dbService.load(1);

        assertThat(cache.getMissCount(), is(0));
        assertThat(cache.getHitCount(), is(1));
    }

    @Test
    public void shouldIncrementMissAfterLifeTime() throws Exception {
        PhoneDataSet phoneDataSet = PhoneDataSet.builder()
                .number("911")
                .build();
        AddressDataSet addressDataSet = AddressDataSet.builder()
                .street("address")
                .build();
        UserDataSet userDataSet = UserDataSet.builder()
                .name("roman")
                .age(10)
                .build();

        phoneDataSet.setUser(userDataSet);
        addressDataSet.setUserDataSet(userDataSet);
        userDataSet.setPhoneDataSetList(Collections.singletonList(phoneDataSet));
        userDataSet.setAddress(addressDataSet);

        dbService.create(userDataSet);
        long overLifeTime = cache.getLifeTimeMs() + 100;

        Thread.sleep(overLifeTime);
        dbService.load(1);

        assertThat(cache.getMissCount(), is(1));
        assertThat(cache.getHitCount(), is(0));
    }

}

