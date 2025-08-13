package com.prep.interview.shorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 3, 6, 9, 12, 2, 5);
        //without sorting
        System.out.println(list);

         list.sort(Comparator.naturalOrder());
        //without sorting
        System.out.println(list);

        list.sort(Comparator.reverseOrder());
        //without sorting
        System.out.println(list);
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
