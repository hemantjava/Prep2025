package com.prep.interview.collection.map.hashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Summary
 * --------
 * <p>
 * putIfAbsent → insert only if absent.
 * <p>
 * compute → recalculate value (even if absent).
 * <p>
 * computeIfAbsent → calculate only if absent.
 * <p>
 * computeIfPresent → calculate only if present.
 * <p>
 * merge → smart add/update with combining logic.
 * <p>
 * replace / replaceAll → update existing values only.
 */

public class NewMethodsInMap {
    static void main() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        // won't override because "A" is already present
        map.putIfAbsent("A", 20);

        //Override the A value
        map.put("A", 40);

        // inserts because "B" is not present
        map.putIfAbsent("B", 30);

        System.out.println(map);//{A=40, B=30}
        map.compute("A", (k, v) -> (v == null) ? 1 : v + 5);//{A=45, B=30}
        System.out.println(map);
        map.putIfAbsent("B", 100); // B already exists → stays 30
        map.computeIfAbsent("D", k -> 200); // D absent → inserts 200
        System.out.println(map);//{A=45, B=30, D=200}
        map.computeIfPresent("A", (k, v) -> v * 2); // A=45
        map.computeIfPresent("E", (k, v) -> v + 10); // does nothing (E absent)
        System.out.println(map);//{A=90, B=30, D=200}
        map.merge("B", 5, Integer::sum); // B=35
        map.merge("F", 100, Integer::sum); // inserts F=100
        System.out.println(map);//{A=90, B=35, D=200, F=100}
        String str = "hsgdkjsjajkjgcfcas";
        charCounts(str);
    }

    private static void charCounts(String str) {
        // Map<Character, Integer> map = new TreeMap<>();//{a=2, c=2, d=1, f=1, g=2, h=1, j=4, k=2, s=3} -> scending order
        // Map<Character, Integer> map = new HashMap<>();//{a=2, c=2, s=3, d=1, f=1, g=2, h=1, j=4, k=2} -> unpredicted order
        Map<Character, Integer> map = new LinkedHashMap<>();//{h=1, s=3, g=2, d=1, k=2, j=4, a=2, c=2, f=1} -> Insertion order preserved
        for (char c : str.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        System.out.println(map);
    }
}
