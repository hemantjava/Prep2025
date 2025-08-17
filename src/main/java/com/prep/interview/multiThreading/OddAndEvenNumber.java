package com.prep.interview.multiThreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class OddAndEvenNumber {
    public static void main(String[] args) {
        printOddAndEvenNumber(20);
    }

    private static void printOddAndEvenNumber(int num) {

        CompletableFuture<Void> oddFuture = CompletableFuture.runAsync(() -> {
            IntStream.rangeClosed(1, num)
                    .boxed()
                    .filter(n -> n % 2 != 0)
                    .forEach(n -> {
                        try {
                            Thread.currentThread().setName("Odd");
                            System.out.println(n + ":" + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    });
        });

        CompletableFuture<Void> evenFuture = CompletableFuture.runAsync(() -> {
            IntStream.rangeClosed(1, num)
                    .boxed()
                    .filter(n -> n % 2 == 0)
                    .forEach(n -> {
                        try {

                            Thread.currentThread().setName("Even");
                            System.out.println(n + ":" + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
        });

        CompletableFuture.allOf(oddFuture, evenFuture).join();
    }

    private static void printOddAndEvenNumber1(int num) {

        CompletableFuture<Void> oddFuture = CompletableFuture.runAsync(() -> {
            IntStream.rangeClosed(1, num)
                    .boxed()
                    .filter(n -> n % 2 != 0)
                    .forEach(n -> {
                        try {
                            Thread.currentThread().setName("Odd");
                            System.out.println(n + ":" + Thread.currentThread().getName());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    });
        });

        CompletableFuture<Void> evenFuture = CompletableFuture.runAsync(() -> {
            IntStream.rangeClosed(1, num)
                    .boxed()
                    .filter(n -> n % 2 == 0)
                    .forEach(n -> {
                        try {
                            Thread.currentThread().setName("Even");
                            System.out.println(n + ":" + Thread.currentThread().getName());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
        });

        CompletableFuture.allOf(oddFuture, evenFuture).join();
    }
}
