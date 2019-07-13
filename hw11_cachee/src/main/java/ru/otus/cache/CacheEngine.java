package ru.otus.cache;


import java.util.function.Function;

public interface CacheEngine<K, V> {
    void put(MyElement<K, V> element);

    MyElement<K, V> get(K key);

    V getV(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    void put(K key, V value);

}
