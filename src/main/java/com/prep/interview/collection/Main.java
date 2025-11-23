package com.prep.interview.collection;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 4, 3, 2, 1, 1, 5, 6, 1, 2, 6, 6, 6, 6, 5};

        Map<Integer, Long> freq = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, LinkedHashMap::new, Collectors.counting()));

        List<Map.Entry<Integer, Long>> collect = freq.entrySet().stream()
                .filter(e -> e.getValue() > 1)                // only duplicates
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue())) // sort by frequency desc
                .collect(Collectors.toList());

        System.out.println(collect);


    }
}
