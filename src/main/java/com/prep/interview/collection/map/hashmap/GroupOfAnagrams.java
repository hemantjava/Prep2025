package com.prep.interview.collection.map.hashmap;

import java.util.*;
import java.util.stream.Collectors;

/*
       EXPECTED OUTPUT:
       ----------------
       1st set:
       [[eat, tea, ate], [tan, nat], [bat]]

       2nd set:
       [[abc, cba, bac], [foo], [bar]]

       3rd set:
       [[listen, silent], [triangle, integral], [garden, ranged]]

   */
public class GroupOfAnagrams {
    public static void main(String[] args) {
        System.out.println("1st set:");
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

        System.out.println("\n2nd set:");
        System.out.println(groupAnagrams(new String[]{"abc", "cba", "bac", "foo", "bar"}));
        System.out.println(groupAnagrams1(new String[]{"abc", "cba", "bac", "foo", "bar"}));

        System.out.println("\n3rd set:");
        System.out.println(groupAnagrams(new String[]{"listen", "silent", "triangle", "integral", "garden", "ranged"}));

        printOddAndEven(List.of(1,2,3,4,5,6,7,8,9));
    }

    public static List<List<String>> groupAnagrams(String[] input) {
        HashMap<String, List<String>> map = new LinkedHashMap<>();
        for (String str : input) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String sortString = String.valueOf(ch);//Array to string
            //if key absent/ new key create entry and return value or else return value only
            map.computeIfAbsent(sortString, v -> new ArrayList<>()).add(str);
        }
        return map.values().stream().toList();
    }

    public static  List<List<String>> groupAnagrams1(String[] input) {
        return Arrays.stream(input)
                .collect(Collectors.groupingBy(str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return String.valueOf(chars);
                }, LinkedHashMap::new, Collectors.toList())).values().stream().toList();
    }

    public static void printOddAndEven(List<Integer> integers){
        integers.stream().collect(Collectors.groupingBy(num -> num % 2 == 0 ? "Even" : "Odd"))
                .forEach((k,v)-> System.out.println(k+" : "+v));
    }

}
