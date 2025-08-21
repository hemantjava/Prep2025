package com.prep.interview.multiThreading.utility;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final CyclicBarrier barrier = new CyclicBarrier(3,
            () -> System.out.println("All parties arrived at barrier, let's proceed!")
    );

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadId + " working...:"+Thread.currentThread().getName());
                    Thread.sleep(1000 * threadId);
                    System.out.println("Thread " + threadId + " waiting at barrier: "+Thread.currentThread().getName());
                    barrier.await(); // wait for others
                    System.out.println("Thread " + threadId + " passed barrier:"+Thread.currentThread().getName());
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}

