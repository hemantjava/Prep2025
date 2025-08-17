package com.prep.interview.string;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringEncoding {
    public static void main(String[] args) {
        String str = "aabbcccdei";
        System.out.println(convertStream(str));// 2a2b3c1d1e1i
        System.out.println(convert(str));// 2a2b3c1d1e1i
        System.out.println(encode(str));// 2a2b3c1d1e1i
    }


    //Using Map collection
    private static String convert(String str) {
        char[] charArray = str.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : charArray) {
            map.put(c, map.merge(c, 1, Integer::sum));
        }
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> sb.append(v).append(k));
        return sb.toString();
    }

    //Using Stream API
    private static String convertStream(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().map(entry -> entry.getValue() + "" + entry.getKey()).collect(Collectors.joining(""));
    }

    //without using collection = using StringBuilder
    public static String encode(String inputString) {
        if (inputString == null || inputString.isBlank())
            return "";
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= inputString.length(); i++) {
            if (i < inputString.length() && inputString.charAt(i) == inputString.charAt(i - 1)) {  //i < inputString.length() adding extra condition
                count++;
            } else {
                sb.append(count).append(inputString.charAt(i - 1)); // starting from 1st char
                count = 1;//reset the count
            }
        }
        //  sb.append(count).append(inputString.charAt(inputString.length() - 1));


        return sb.toString();
    }


}
