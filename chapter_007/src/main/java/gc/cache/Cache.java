package gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class Cache<K, V> {

    private Map<K, SoftReference<V>> map  =  new HashMap<>();

    protected abstract V loadData(K key);

    public V add(K key) {
        V value = this.loadData(key);
        map.put(key, new SoftReference<>(value));
        return value;
    }

    public V get(K key) {
        V value = map.get(key).get();
        if (value == null) {
            value = this.add(key);
        }
        return value;
    }
}
