package com.prep.interview.leetCode;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Take the first string as the initial prefix
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // Keep reducing the prefix until it matches the current string's prefix
            while (strs[i].indexOf(prefix) != 0) { //indexOf retun '0' if value matching
                prefix = prefix.substring(0, prefix.length() - 1);

                // If no common prefix
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}
