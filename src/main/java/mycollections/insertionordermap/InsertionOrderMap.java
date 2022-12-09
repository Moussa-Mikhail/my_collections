package mycollections.insertionordermap;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Moussa
 * Map whose entries can be iterated in the order they were inserted.
 */
public class InsertionOrderMap<K, V> implements Map<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final List<K> list = new ArrayList<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V remove(Object key) {
        list.remove(key);
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            list.add(key);
        }
        return map.put(key, value);
    }

    @Override
    public void clear() {
        map.clear();
        list.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (K key : list) {
            action.accept(key, map.get(key));
        }
    }
}
