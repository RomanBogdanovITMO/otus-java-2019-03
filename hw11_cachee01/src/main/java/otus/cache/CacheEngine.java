package otus.cache;

public interface CacheEngine<K, V> {
    void put(K key, V value);

    V get(K key);

    int getMaxElements();

    long getLifeTimeMs();

    boolean isEternal();

    int getHitCount();

    int getMissCount();

    void dispose();
}
