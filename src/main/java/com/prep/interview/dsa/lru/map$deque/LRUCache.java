package com.prep.interview.dsa.lru.map$deque;

import java.util.*;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        //accessOrder – the ordering mode - true for access-order, false for insertion-order
        this.capacity = capacity;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}
