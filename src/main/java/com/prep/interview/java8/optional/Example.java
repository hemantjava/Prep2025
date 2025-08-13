package com.prep.interview.java8.optional;

import java.util.Optional;

public class Example {
    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable(null);
        name.map(String::toUpperCase).ifPresent(System.out::println);

        String name1 = null;
        Optional<String> opt = Optional.ofNullable(name1); // ✅ Returns Optional.empty()


        String nullName = null;
        Optional<String> optNull = Optional.of(nullName); // ❌ Throws NullPointerException


    }
}
