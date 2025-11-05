package com.prep.interview.multi_threading.blockingqueue;

import java.util.concurrent.BlockingQueue;

public record Consumer(BlockingQueue<Integer> queue) implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take(); // Blocks if queue is empty
                if (value == -1) { // Poison pill detected
                    System.out.println("Consumer received stop signal. Exiting...");
                    break;
                }
                System.out.println(STR."\{Thread.currentThread().getName()} :Consumer consumed: \{value}");
                Thread.sleep(800); // Simulate processing time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
