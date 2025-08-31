package com.prep.interview.java21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

public class Java21Examples {
    public static void main(String[] args) throws IOException {
        try (ExecutorService executor = newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Running on: " + Thread.currentThread()));
            executor.shutdown();
            {

            }

            Deque<Integer> dq = new ArrayDeque<>(List.of(1, 2, 3));
            System.out.println(dq.getFirst()); // 1
            System.out.println(dq.getLast());  // 3

        }
    }
}
