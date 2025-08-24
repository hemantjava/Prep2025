package com.prep.interview.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Combine2Lists {
    static void main() {
        List<String> l1 = Arrays.asList("aa", "bb", "cc");
        List<String> l2 = Arrays.asList("bb", "aa", "dd");
         Stream.of(l1, l2).flatMap(List::stream)     //of method return stream of stream
                .filter(Predicate.isEqual("aa"))
                .toList().forEach(System.out::println);

         Stream.concat(l1.stream(), l2.stream())  //concat method return stream
                 .filter(Predicate.isEqual("aa"))
                 .toList().forEach(System.out::println);

    }
}
