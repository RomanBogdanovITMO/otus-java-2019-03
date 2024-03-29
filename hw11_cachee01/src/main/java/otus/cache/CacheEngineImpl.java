package otus.cache;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K,V> {
    private static final int TIME_THRESHOLD_MS = 5;
    public static final String MAX_ELEMENTS = "max_elements";
    public static final String LIFE_TIME_MS = "life_time_ms";
    public static final String IDLE_TIME_MS = "idle_time_ms";
    public static final String IS_ETERNAL = "is_eternal";

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private int hit = 0;
    private int miss = 0;
    private final Map<K, SoftReference<MyElement<K, V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();



    public static class Builder<K, V> {
        private int maxElements;
        private long lifeTimeMS;
        private long idleTimeMS;
        private boolean isEternal;

        public Builder<K, V> maxElements(int quantity) {
            maxElements = quantity;
            return this;
        }

        public Builder<K, V> lifeTimeMS(long ms) {
            lifeTimeMS = ms;
            return this;
        }

        public Builder<K, V> idleTimeMS(long ms) {
            idleTimeMS = ms;
            return this;
        }

        public Builder<K, V> isEternal(boolean flag) {
            isEternal = flag;
            return this;
        }

        public CacheEngine<K, V> build() {
            return new CacheEngineImpl<>(maxElements, lifeTimeMS, idleTimeMS, isEternal);
        }
    }

    private CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    @Override
    public void put(final K key, final V value) {
        if (elements.size() == maxElements) {
            final K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }
        elements.put(key, new SoftReference<>(new MyElement<>(key, value)));
        setTimerTask(key);
    }

    private void setTimerTask(final K key) {
        if (!isEternal) {
            if (lifeTimeMs != 0) {
                final TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                final TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    private TimerTask getTimerTask(final K key, final Function<MyElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                final MyElement<K, V> element = getElement(key);
                if (Objects.isNull(element) || isT1beforeT2(timeFunction.apply(element), System.currentTimeMillis()))
                    elements.remove(key);
                this.cancel();
            }
        };
    }

    private boolean isT1beforeT2(final long t1, final long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    @Override
    public V get(final K key) {
        final MyElement<K, V> element = getElement(key);
        if (Objects.nonNull(element)) {
            hit++;
            element.setAccessed();
            return element.getValue();
        } else {
            miss++;
            return null;
        }
    }

    private MyElement<K, V> getElement(final K key) {
        final SoftReference<MyElement<K, V>> softReference = elements.get(key);
        if (Objects.nonNull(softReference))
            return softReference.get();
        return null;
    }

    @Override
    public int getMaxElements() {
        return maxElements;
    }

    @Override
    public long getLifeTimeMs() {
        return lifeTimeMs;
    }

    @Override
    public boolean isEternal() {
        return isEternal;
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }
}
