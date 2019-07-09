package ru.otus.cache;

import java.util.Map;

public interface CacheEngine<K, V> {

    void put(K key, V value);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    Map<String, Object> getCacheInfo();
}