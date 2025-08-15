package com.prep.interview.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternPrint {
    public static void main(String[] args) {
        List<String> input_list = List.of("John", "Mark", "Juli", "Bob", "Rick", "John", "Juli");
        //output_list=[John_1,Mark_1,Juli_1,Bob_1,Rick_1,John_2,Juli_2]

        System.out.println(printPatterns(input_list));
    }
    private static List<String> printPatterns(List<String> inputList) {
        Map<String, Integer> map = new HashMap<>();//for name counting -- map.merge()
        return inputList.stream().map(name -> name + "_" + map.merge(name, 1, Integer::sum)).toList();
    }

}
