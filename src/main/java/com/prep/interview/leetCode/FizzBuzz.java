package com.prep.interview.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FizzBuzz{ //LeetCode-4412
    public static void main(String[] args) {

    }

    private static List<String> fizzBuzz(int x){
        List<String> list = new ArrayList<>();
        for (int i = 0; i <=x ; i++) {
            if (i%3==0 && i%5==0){
                list.add("FizzBuzz");
            }else if (i%3==0){
                list.add("Fizz");
            }else if (i%5==0){
                list.add("Buzz");
            }else {
                list.add(String.valueOf(i));
            }

        }
        return list;
    }
}
