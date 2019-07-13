package ru.otus.db;

import ru.otus.cache.CacheEngine;
import ru.otus.cache.MyElement;
import ru.otus.dataset.UserDataSet;

public class CacheDBSerivceImpl implements DBService {
    private  CacheEngine<Long, UserDataSet> cache;

    public CacheDBSerivceImpl() {
        DBServiceHiber serviceHiber = new DBServiceHiber();
    }

    public CacheDBSerivceImpl(CacheEngine<Long, UserDataSet> cache) {
        this.cache = cache;
    }

    @Override
    public void create(UserDataSet dataSet) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        MyElement<Long, UserDataSet> value = cache.get(dataSet.getId());//Exception in thread "main" java.lang.NullPointerException
        if (value == null) {
            serviceHiber.create(dataSet);
            cache.put(dataSet.getId(),dataSet);
        }
        cache.put(value.getKey(),dataSet);
    }

    @Override
    public UserDataSet load(long id) {
        DBServiceHiber serviceHiber = new DBServiceHiber();
        MyElement<Long, UserDataSet> value = cache.get(id);
        if (value == null){
            return serviceHiber.load(id);
        }
        return serviceHiber.load(value.getKey());
    }


}
