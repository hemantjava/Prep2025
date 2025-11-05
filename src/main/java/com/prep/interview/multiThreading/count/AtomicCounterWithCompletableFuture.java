package com.prep.interview.multiThreading.count;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class AtomicCounterWithCompletableFuture {

    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet(); // Atomic and lock-free
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        AtomicCounterWithCompletableFuture example = new AtomicCounterWithCompletableFuture();

        int numberOfTasks = 10;
        int incrementsPerTask = 1000;

        // Create a list of CompletableFutures
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < incrementsPerTask; j++) {
                    example.increment();
                }
            });
            futures.add(future);
        }

        // Wait for all tasks to complete
        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        System.out.println("Final Counter (CompletableFuture + Atomic): " + example.getCounter());
    }
}
