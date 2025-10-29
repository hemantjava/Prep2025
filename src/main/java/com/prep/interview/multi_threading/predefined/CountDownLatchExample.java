package com.prep.interview.multi_threading.predefined;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CountDownLatchExample {

    public static void main(String[] args) {
        final int numberOfWorkers = 3;
        final CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        // Create and start worker threads by main thread
        for (int i = 1; i <= numberOfWorkers; i++) {
            new Thread(new WorkerThread(latch, "Worker-" + i)).start();
        }

        try {
            System.out.println("Main thread is waiting for workers to finish...");
            latch.await(); // Wait until the count reaches zero
            System.out.println("All workers have finished. Main thread proceeding.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

record WorkerThread(CountDownLatch latch, String name) implements Runnable {

    @Override
    public void run() {
        System.out.println(name + " is working...");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1, 6) * 2000L); // Simulate work
            System.out.println(name + " has finished work.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown();// Decrement the count of the latch
        }
    }
}
