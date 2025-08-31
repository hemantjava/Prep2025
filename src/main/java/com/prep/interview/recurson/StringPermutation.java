package com.prep.interview.recurson;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {
    public static void main(String[] args) {
        String str = "abc";
        List<String> result = new ArrayList<>();
        permute("", str, result);

        System.out.println("All permutations of " + str + " are:");
        result.forEach(System.out::println);
    }

    static void permute(String prefix, String remaining, List<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permute(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1),
                        result);
            }
        }
    }
}
