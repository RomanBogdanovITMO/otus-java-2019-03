package ru.otus.db;

import ru.otus.cache.CacheEngine;
import ru.otus.cache.MyElement;
import ru.otus.dataset.UserDataSet;

public class CacheDBServiceImpl implements DBService {
    private  CacheEngine<Long, UserDataSet> cache;

    public CacheDBServiceImpl() {
        DBServiceHiber serviceHiber = new DBServiceHiber();
    }

    public CacheDBServiceImpl(CacheEngine<Long, UserDataSet> cache) {
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
        UserDataSet dataSet = cache.get(id);
        if (dataSet == null){
            dataSet = serviceHiber.load(id);
            if (dataSet != null)
                cache.put(dataSet.getId(),dataSet);
        }
        return serviceHiber.load(id);
    }


}
