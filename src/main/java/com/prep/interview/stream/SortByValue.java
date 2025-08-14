package com.prep.interview.stream;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortByValue {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap();
        map.put(1,3);
        map.put(2,1);
        map.put(3,2);
        System.out.println(map);
        LinkedHashMap<Integer, Integer> collect =
                map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue(), (a, b) -> a, LinkedHashMap::new));
        System.out.println(collect);
    }
}
