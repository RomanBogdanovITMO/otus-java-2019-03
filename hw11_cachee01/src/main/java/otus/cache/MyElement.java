package otus.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MyElement<K,V>  {

    private final K key;
    private final V value;
    private final long creationTime;
    private long lastAccessTime;


    public MyElement(K key, V value) {
        this.key = key;
        this.value = value;
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}
