package com.prep.interview.stream;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RemoveVowels {
    public static void main(String[] args) {
        String string = "hemantsahu";
        System.out.println(remoVowels(string));
    }

    private static String remoVowels(String string) {
        return string.chars().mapToObj(c ->(char)c)
                .filter(Predicate.not(RemoveVowels::isVowel))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".contains(String.valueOf(c));
    }

}
