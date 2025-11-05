package com.prep.interview.multiThreading.count;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicCounterWithLock {

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

    static void main() throws InterruptedException {

        AtomicCounterWithLock example = new AtomicCounterWithLock();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Total count is :" + example.count);


    }

}
