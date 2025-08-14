package com.prep.interview.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//Merge to list and remove duplicates
public class MergeToList {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);

        List<Integer> list = Stream.concat(list2.stream(), list1.stream())
                .distinct().toList();
        System.out.println(list);
    }
}
