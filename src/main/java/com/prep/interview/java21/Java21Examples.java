package com.prep.interview.java21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java21Examples {
    public static void main(String[] args) throws IOException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(() -> System.out.println("Running on: " + Thread.currentThread()));
        executor.shutdown();

        Deque<Integer> dq = new ArrayDeque<>(List.of(1, 2, 3));
        System.out.println(dq.getFirst()); // 1
        System.out.println(dq.getLast());  // 3

    }
}
