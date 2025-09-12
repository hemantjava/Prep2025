package com.prep.interview.collection.map.hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class ItineraryExample {
    public static void main(String[] args) {
        Map<String, String> map = Map.of(
                //from   ->  to
                    "Chennai", "Bengaluru",
                "Mumbai", "Delhi",
                "Goa", "Chennai",
                "Delhi", "Goa");
        //from  -> to
        System.out.println("\n=================");
        System.out.println(getPathList(map));//Mumbai-->Delhi-->Goa-->Chennai-->Bengaluru
        System.out.println(getPathList1(map));//Mumbai-->Delhi-->Goa-->Chennai-->Bengaluru
    }


    private static String getPathList(Map<String, String> map) {
        //Getting starting point from FROM which not present in To side
        String start = map.keySet().stream().filter(e -> !map.values().contains(e))
                .findAny().orElse("");
        List<String> stringList = new ArrayList<>(map.size());//added in list
        stringList.add(start);
        for (int i = 0; i < map.size(); i++) {
            start = map.get(start);
            stringList.add(start);
        }
        return stringList.stream().collect(Collectors.joining("==>"));
    }

    private static String getPathList1(Map<String, String> map) {
        // Find the starting point (a key that's not present in the values)
        String start = map.keySet().stream()
                .filter(e -> !map.containsValue(e))
                .findFirst()
                .orElse("");

        List<String> path = new ArrayList<>();

        // Traverse until there is no next node
        while (start != null) {
            path.add(start);
            start = map.get(start);
        }

        return String.join("-->", path);
    }


}
