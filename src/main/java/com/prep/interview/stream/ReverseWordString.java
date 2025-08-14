package com.prep.interview.stream;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWordString {
    public static void main(String[] args) {
        String sentence = "Java 8 Stream API";
        String reversed = Arrays.stream(sentence.split(" "))//word
                .map(word -> word.chars()
                        .mapToObj(c -> String.valueOf((char) c))//String from character
                        .reduce("", (a, b) -> b + a))
                .collect(Collectors.joining(" "));

        System.out.println(reversed);
    }
}
