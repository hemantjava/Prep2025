package com.prep.interview.multiThreading.blockingqueue;

import java.util.concurrent.BlockingQueue;

public record Producer(BlockingQueue<Integer> queue) implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+" :Producer produced: " + i);
                queue.add(i); // Blocks if queue is full
                Thread.sleep(500); // Simulate time to produce
            }
            queue.put(-1); // Poison pill to signal consumer to stop
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
