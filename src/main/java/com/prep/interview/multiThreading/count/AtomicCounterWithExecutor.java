package com.prep.interview.multiThreading.count;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterWithExecutor {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet(); // Atomic operation
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounterWithExecutor example = new AtomicCounterWithExecutor();

        int numberOfThreads = 10;
        int incrementsPerThread = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        Runnable task = () -> {
            for (int i = 0; i < incrementsPerThread; i++) {
                example.increment();
            }
        };

        // Submit all tasks
        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(task);
        }

        // Shutdown executor
        executor.shutdown();
        //Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final Counter (Atomic + Executor): " + example.getCounter());
    }
}
