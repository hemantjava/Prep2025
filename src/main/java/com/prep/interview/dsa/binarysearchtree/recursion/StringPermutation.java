package com.prep.interview.dsa.binarysearchtree.recursion;

public class StringPermutation {

    public static void permute(String str, String result) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char picked = str.charAt(i);
            String remaining = str.substring(0, i) + str.substring(i + 1);
            permute(remaining, result + picked);
        }
    }

    public static void main(String[] args) {
        String str = "abcd";
        permute(str, "");
    }
}
