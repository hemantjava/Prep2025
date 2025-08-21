package com.prep.interview.dsa.lru;


public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 10); // least added
        cache.put(2, 20);
        cache.put(3, 30);// recent added
        System.out.println(cache);//{1=10, 2=20, 3=30}
        cache.put(4, 40);
        System.out.println(cache);//{2=20, 3=30, 4=40}
        System.out.println(cache.get(3));// true for access-order
        System.out.println(cache);//{2=20, 4=40, 3=30}
    }
}
