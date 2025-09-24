package com.prep.interview.collection.map.hashmap;

import java.util.*;

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

        System.out.println("\n3rd set:");
        System.out.println(groupAnagrams(new String[]{"listen", "silent", "triangle", "integral", "garden", "ranged"}));

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

}
