package com.prep.interview.dsa.lru;

import java.util.*;

//inheritance
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;


    public LRUCache(int capacity) {
       super(capacity, 0.75F, true);
        //accessOrder – the ordering mode - true for access-order, false for insertion-order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
// composition

class MyLRUCache<K, V> {

    private final LinkedHashMap<K, V> map;

    public MyLRUCache(int capacity) {
        map = new LinkedHashMap<>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public synchronized V get(K key) {
        return map.getOrDefault(key, null);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

class InterLRUCache<K, V> {

    private final int capacity;
    private final HashMap<K, V> map;
    private final LinkedList<K> usageOrder;

    public InterLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.usageOrder = new LinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        // Move key to the end (most recently used)
        usageOrder.remove(key);
        usageOrder.addLast(key);

        return map.get(key);
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            // Update value and mark as recently used
            map.put(key, value);
            usageOrder.remove(key);
            usageOrder.addLast(key);
        } else {
            if (map.size() >= capacity) {
                // Evict least recently used
                K oldestKey = usageOrder.removeFirst();
                map.remove(oldestKey);
            }
            map.put(key, value);
            usageOrder.addLast(key);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (K key : usageOrder) {
            sb.append("(").append(key).append(":").append(map.get(key)).append(") ");
        }
        return sb.toString();
    }
}
