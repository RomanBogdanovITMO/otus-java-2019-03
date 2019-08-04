package ru.otus.cache;



public interface CacheEngine<K, V> {
    void put(K key, V value);

    V get(K key);

    int getMaxElements();
 hw12_webe-server

    long getLifeTimeMs();

    long getIdleTimeMs();



    long getLifeTimeMs();

    long getIdleTimeMs();

 hw01-maven
    boolean isEternal();

    int getHitCount();

    int getMissCount();

    void dispose();
 hw12_webe-server


 hw01-maven
}
