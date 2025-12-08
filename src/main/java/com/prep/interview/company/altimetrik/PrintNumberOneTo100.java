package com.prep.interview.company.altimetrik;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Print numbers from 1 to 100 without using 3 threads.
public class PrintNumberOneTo100 {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(3)){
            CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> printNumbers(1, 34), service);
            CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> printNumbers(35, 67), service);
            CompletableFuture<Void> cf3 = CompletableFuture.runAsync(() -> printNumbers(68, 100), service);
            CompletableFuture.allOf(cf1, cf2, cf3).join();
        }
        System.out.println("✅ All tasks completed!");
    }


    private static void printNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " - " + Thread.currentThread().getName());
        }
    }
}
