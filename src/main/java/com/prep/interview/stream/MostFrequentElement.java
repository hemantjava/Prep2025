package com.prep.interview.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostFrequentElement {

    public static void main(String[] args) {
        String str = "banana";
        String frequentLetter = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).stream().findFirst().orElse(null);
        System.out.println(frequentLetter);
    }
}
