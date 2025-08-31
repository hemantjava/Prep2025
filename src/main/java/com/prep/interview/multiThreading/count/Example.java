package com.prep.interview.multiThreading.count;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example {
    static void main() {



    }
    Lock lock = new ReentrantLock();
    int count = 0;
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
