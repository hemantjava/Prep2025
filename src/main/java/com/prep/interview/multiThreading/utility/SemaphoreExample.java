package com.prep.interview.multiThreading.utility;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(2); // 2 permits thread at a time

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire(); // get permit
                    System.out.println("Thread " + threadId + " acquired permit: "+Thread.currentThread().getName());
                    Thread.sleep(1000); // simulate work
                    System.out.println("Thread " + threadId + " releasing permit: "+Thread.currentThread().getName());
                    semaphore.release(); // release permit
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
