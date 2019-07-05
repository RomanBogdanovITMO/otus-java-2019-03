package ru.otus.db;

import ru.otus.cache.CacheEngine;
import ru.otus.dataset.UserDataSet;

public class CacheDBSerivceImpl implements DBService {
    private final CacheEngine<Long, UserDataSet> cache;

    public CacheDBSerivceImpl(CacheEngine<Long, UserDataSet> cache) {
        this.cache = cache;
    }

    @Override
    public void create(UserDataSet dataSet) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        serviceHiber.create(dataSet);
        cache.put(dataSet.getId(),dataSet);
    }

    @Override
    public UserDataSet load(long id) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        return cache.getOrCalculate(id,serviceHiber ::load);
    }
}
