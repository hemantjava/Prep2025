package com.prep.interview.multiThreading.blockingqueue;

import java.util.concurrent.BlockingQueue;

public record Consumer(BlockingQueue<Integer> queue) implements Runnable {
    @Override
    public void run() {
        try {
            Integer value = queue.remove();//take a value from the queue
            System.out.println("Consumer: " + value +":"+Thread.currentThread().getName());
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted");
        }

    }
}
