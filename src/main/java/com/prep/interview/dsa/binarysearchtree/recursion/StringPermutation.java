package com.prep.interview.dsa.binarysearchtree.recursion;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

    public static void main(String[] args) {
        String str = "abc";
        permute("", str);
        // ----------------------
        List<String> list = new ArrayList<>();
        String val = "abc";
        permute("", val, list);
        System.out.println(list);

    }

    private static void permute(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permute(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1));
            }
        }
    }

    private static void permute(String prefix, String remaining, List<String> list) {
        if (remaining.isEmpty()) {
            list.add(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permute(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1), list);
            }
        }
    }
}
