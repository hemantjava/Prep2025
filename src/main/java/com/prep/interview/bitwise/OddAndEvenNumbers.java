package com.prep.interview.bitwise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OddAndEvenNumbers {
    static void main() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<String, List<Integer>> evenOdd = list.stream()
                .collect(Collectors.groupingBy(n -> (n & 1) == 0 ? "Even Number" : "Odd Number"));
        evenOdd.forEach((key, value) -> {
            System.out.println(key+":"+value);
        });
    }
}
