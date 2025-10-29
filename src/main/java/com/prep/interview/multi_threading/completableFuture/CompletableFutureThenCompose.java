package com.prep.interview.multi_threading.completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThenCompose {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " World"))
                .thenAccept(System.out::println)
                .join();


    }
}
