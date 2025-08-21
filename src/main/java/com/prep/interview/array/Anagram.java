package com.prep.interview.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static void main(String[] args) {
        System.out.println(isValidAnagram("anagram", "mnagraa"));
        System.out.println(isAnagram("anagram", "mnagraa"));
       System.out.println(isAnagramMap("anagrAM", "MnAgraa"));
        System.out.println('A'- 'a');
    }

    private static boolean isValidAnagram(String source, String target) {

        if (source.length() != target.length()) {
            return false;
        }
        char[] array1 = source.toCharArray();
        char[] array2 = target.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    //preferred one

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // for lowercase a-z  (0-25)

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int val : count) {
            if (val != 0) return false;
        }
        return true;
    }

    public static boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        // Count frequency of chars in s
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Subtract frequency using t
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false; // char not in s
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c); // clean up
            }
        }

        return map.isEmpty(); // all balanced
    }
}
