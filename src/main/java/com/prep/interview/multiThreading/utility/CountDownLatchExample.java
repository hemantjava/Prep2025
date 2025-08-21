package com.prep.interview.multiThreading.utility;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        // Worker threads
        for (int i = 1; i <= 3; i++) {
            int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " started work: "+Thread.currentThread().getName());
                    Thread.sleep(1000 * workerId);
                    System.out.println("Worker " + workerId + " finished: "+Thread.currentThread().getName());
                    latch.countDown(); // reduce latch
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        latch.await(); // wait until the count reaches 0
        // Main thread waits
        System.out.println("Main thread waiting...");

        System.out.println("All workers finished. Main thread continues...");
    }
}
