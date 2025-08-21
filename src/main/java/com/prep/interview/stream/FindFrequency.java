package com.prep.interview.stream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindFrequency {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 1,4,2,3,4,2,2,1);
        list.stream().collect(Collectors.toMap(Function.identity(),v -> 1,Integer::sum, LinkedHashMap::new))
                .forEach((x,y)->System.out.println(x+":"+y));
    }
}
