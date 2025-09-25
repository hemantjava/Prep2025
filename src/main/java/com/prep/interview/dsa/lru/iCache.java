package com.prep.interview.dsa.lru;

public interface iCache<K, V> {
     V get(K k);
     V put(K k, V v);
     void printCache();
}
