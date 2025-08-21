package com.prep.interview.multiThreading.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionHandling {

    public static void main(String[] args) {
        exceptionally();
        System.out.println("----------------------------------------------------");
        handle();

    }

    private static void exceptionally() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // simulate exception
            if (true) throw new RuntimeException("Something went wrong!");
            return "Success";
        });

        // Handle exception & provide fallback
        String result = future.exceptionally(ex -> {
            System.out.println("Exception: " + ex.getMessage());
            return "Fallback Value";
        }).join();

        System.out.println("Result: " + result);
    }

    //Handle both success & failure in one place
    private static void handle() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Database error!");
        });

        String result = future.handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Handled exception: " + ex.getMessage());
                return "Default Value";
            }
            return res;
        }).join();

        System.out.println("Result: " + result);
    }
}