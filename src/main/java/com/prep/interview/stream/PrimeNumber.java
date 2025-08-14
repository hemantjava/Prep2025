package com.prep.interview.stream;

import java.util.List;
import java.util.stream.IntStream;

public class PrimeNumber {
    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(2, 100)
                .filter(n -> IntStream.rangeClosed(2, (int) Math.sqrt(n))
                        .allMatch(div -> n % div != 0))
                .boxed().toList();
        System.out.println(list);
    }
}
