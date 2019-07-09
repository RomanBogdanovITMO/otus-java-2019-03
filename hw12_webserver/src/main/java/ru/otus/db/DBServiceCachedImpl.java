package ru.otus.db;

import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.dataset.UserDataSet;

public class DBServiceCachedImpl implements DBService {
    private DBServiceHiber hiber;
    CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>();
    int numberOfDBReads = 0;

    public DBServiceCachedImpl() {
        hiber = new DBServiceHiber();
    }
    public DBServiceCachedImpl(CacheEngine<Long, UserDataSet> cache) {
        DBServiceHiber hiber = new DBServiceHiber();
        this.cache = cache;
    }

    @Override
    public String getLocalStatus() {
        return null;
    }

    @Override
    public void create(UserDataSet dataSet) {
       // DBServiceHiber hiber = new DBServiceHiber();
        hiber.create(dataSet);
        if (dataSet.getId() >= 0) {
            cache.put(dataSet.getId(), dataSet);
            System.out.println("user inserted into cache ID:" + dataSet.getId());
        }
    }

    @Override
    public UserDataSet load(long id) {
       // DBServiceHiber hiber = new DBServiceHiber();
        UserDataSet dataSet = cache.get(id);
        if (dataSet != null) {
            System.out.println("user read from cache ID: " + id);
            System.out.println("Cache hit count = " + cache.getHitCount());
            System.out.println("Cache miss count = " + cache.getMissCount());
            System.out.println("Number of DB reads = " + numberOfDBReads);
            return dataSet;
        }
        numberOfDBReads++;
        return hiber.load(id);
    }

    @Override
    public void shutdown() {
       // DBServiceHiber hiber = new DBServiceHiber();
        hiber.shutdown();
        cache.dispose();
    }
}
