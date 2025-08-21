package com.prep.interview.multiThreading.blockingqueue;

import java.util.concurrent.BlockingQueue;

public record Producer(BlockingQueue<Integer> queue) implements Runnable {

    @Override
    public void run() {
        try {
            int value = 0;
            while (true) {
                System.out.println("Producer: " + value +":"+Thread.currentThread().getName());
                queue.put(value++);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
