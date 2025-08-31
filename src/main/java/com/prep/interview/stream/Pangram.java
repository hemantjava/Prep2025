package com.prep.interview.stream;

import java.util.HashSet;
import java.util.Set;

public class Pangram {
    static void main(String[] args) {
        String sentence = "thequickbrownfoxjumpsoverthelazydog";
        System.out.println(checkIfPangram1(sentence));
        System.out.println(checkIfPangram(sentence));
    }

    public static boolean checkIfPangram(String sentence) {

        if (sentence.length() < 26)
            return false;
        Set<Character> set = new HashSet<>();
        for (char c : sentence.toCharArray()) {
            if (!(Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'z'))
                return false;
            else set.add(c);
        }
        return set.size() == 26;

    }

    //best approach
    public static boolean checkIfPangram1(String s) {

        int[] arr = new int[26];

        for (char ch : s.toCharArray()) {
            arr[ch - 'a'] += 1;
        }

        for (int i : arr) {
            if (i < 1) {
                return false;
            }
        }

        return true;
    }
}