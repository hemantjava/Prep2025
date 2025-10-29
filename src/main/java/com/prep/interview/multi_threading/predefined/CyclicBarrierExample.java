package com.prep.interview.multi_threading.predefined;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class CyclicBarrierExample {

    public static void main(String[] args) {
         int numberOfThreads = 3;
        // Barrier for numberOfThreads threads, with an action to run once all arrive
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads,
                () -> System.out.println("All threads reached the barrier. Proceeding together!"));

        // Start 3 worker threads
        for (int i = 1; i <= 6; i++) {
            new Thread(new Task(barrier, "Thread-" + i)).start();
        }
    }

    record Task(CyclicBarrier barrier, String name) implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(name + " performing initial work...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 6) * 2000L); // simulate work
                System.out.println(name + " waiting at barrier...");

                // Wait until all threads reach here
                barrier.await();

                // After barrier tripped, all continue
                System.out.println(name + " continues after barrier!");
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
