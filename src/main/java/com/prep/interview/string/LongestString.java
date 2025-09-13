package com.prep.interview.string;

import java.util.Arrays;
import java.util.Comparator;

public class LongestString {
    static void main() {
        String[] stringList1 = {"apple", "banana", "kiwi", "pear"};
        System.out.println(findLongestString(stringList1));
    }

    private static String findLongestString(String[] stringList1) {
        return Arrays.stream(stringList1)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
