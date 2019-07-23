package ru.otus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.db.DBService;

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
        dbService = new ru.otus.db.CacheDBServiceImpl(cache);
    }

    @After
    public void shutDown() throws Exception {
        System.out.println("Succsessuf");
    }

    @Test
    public void shouldIncrementHit() {
        UserDataSet user = new UserDataSet("name", 28,new AddressDataSet("address"),
                new PhoneDataSet("911"));
        dbService.create(user);

        dbService.load(1);

        assertThat(cache.getMissCount(), is(0));
        assertThat(cache.getHitCount(), is(1));
    }

    @Test
    public void shouldIncrementMissAfterLifeTime() throws Exception {
        UserDataSet user = new UserDataSet("name", 28,
                new AddressDataSet("street"),
                new PhoneDataSet("03"));

        dbService.create(user);
        long overLifeTime = cache.getLifeTimeMs() + 100;

        Thread.sleep(overLifeTime);
        dbService.load(1);

        assertThat(cache.getMissCount(), is(1));
        assertThat(cache.getHitCount(), is(0));
    }
}
