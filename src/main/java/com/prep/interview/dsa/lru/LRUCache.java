package com.prep.interview.dsa.lru;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

class MyLRUCache<K, V> implements iCache<K, V> {

    private final LinkedHashMap<K, V> map;

    public MyLRUCache(int capacity) {
        map = new LinkedHashMap<>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public synchronized V get(K key) {
        return map.getOrDefault(key, null);
    }

    @Override
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public void printCache() {
        map.forEach((k, v) -> {
            System.out.println(k + ": \t" + v);
        });
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
        this.map = new HashMap<>(capacity);
        this.usageOrder = new LinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        V v = map.get(key);
        // Move key to the end (most recently used)
        usageOrder.remove(key);
        usageOrder.addLast(key);

        return v;
    }

    public V put(K key, V value) {
        V v = null;
        if (map.containsKey(key)) {
            // Update value
            v = map.put(key, value);
            //and mark as recently used
            usageOrder.remove(key);
            usageOrder.addLast(key);
        } else { // new entry maybe capacity is not there
            if (map.size() >= capacity) {
                // Evict least recently used
                K oldestKey = usageOrder.removeFirst();
                map.remove(oldestKey);
            }
            v = map.put(key, value);
            usageOrder.addLast(key);
        }
        return v;
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

class LRUCacheCH<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> map;
    private final LinkedList<K> order; // maintains access order
    private final Lock lock;

    public LRUCacheCH(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>(capacity);
        this.order = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    public V get(K key) {
        lock.lock();
        try {
            if (!map.containsKey(key)) {
                return null;
            }
            // Move key to front (most recently used)
            order.remove(key);
            order.addLast(key);
            return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                // Update and refresh order
                map.put(key, value);
                order.remove(key);
                order.addLast(key);
            } else {
                if (map.size() >= capacity) {
                    // Evict least recently used (tail)
                    K lru = order.removeFirst();
                    map.remove(lru);
                }
                map.put(key, value);
                order.addLast(key);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        order.forEach(k -> {
            sb.append(k).append("=").append(map.get(k)).append(",");
        });
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public void printCache() {
        lock.lock();
        try {
            order.forEach(k -> System.out.println(k + ": \t" + map.get(k)));

        } finally {
            lock.unlock();
        }
    }
}
