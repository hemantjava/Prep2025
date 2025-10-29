package com.prep.interview.multi_threading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class OddAndEvenNumber {
    public static void main(String[] args){
        try (ExecutorService service = Executors.newFixedThreadPool(2)) {
            AtomicInteger counter = new AtomicInteger(1);
            CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
            while (counter.get() <= 10){
                int num = counter.getAndIncrement();
                future = future.runAsync(() -> {
                    if (num % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " -> Even: " + num);
                    } else {
                        System.out.println(Thread.currentThread().getName() + " -> Odd: " + num);
                    }
                }, service);
            }
            future.join();
        }
    }


}
