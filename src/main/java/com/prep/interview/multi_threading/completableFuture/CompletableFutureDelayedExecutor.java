package com.prep.interview.multi_threading.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDelayedExecutor {
    public static void main(String[] args) {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "Delayed Task",
                        CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS))
                .thenAccept(System.out::println);// Output after 2 seconds: Delayed Task

        voidCompletableFuture.join();
    }
}
