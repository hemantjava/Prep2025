package com.prep.interview.multi_threading.executorService;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;


public class ExecutorServiceExample {

    public static void main(String[] args) {

        try (ExecutorService executorService = newVirtualThreadPerTaskExecutor()) {


            for (int i = 0; i < 30; i++) {
                int finalI = i;
                executorService.execute(() -> System.out.println(finalI + "  Running:" + Thread.currentThread().getName()));
            }
            executorService.shutdown();

        }
    }
}
