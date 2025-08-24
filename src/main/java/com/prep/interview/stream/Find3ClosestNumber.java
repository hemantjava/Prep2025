package com.prep.interview.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Find3ClosestNumber {
    static void main(String[] args) {
        int[] nums = new int[]{4, 10, 3, 7, 5, 15,20};
        int target = 10;
        int num = 3;
        System.out.println(find3Closest(nums, target, num));//5,7,15
    }

    private static List<Integer> find3Closest(int[] nums, int target, int num) {
        return Arrays.stream(nums).boxed()
                .filter(Predicate.not(n -> n == 10))
                .sorted(Comparator.comparingInt(n ->  Math.abs(target -n)))
                .limit(num)
                .toList();

    }
}
