package com.prep.interview.java8.foreach;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

//Example of andThen
public class Example {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack","Hemant");
        Consumer<String> consumer = System.out::print;
        Consumer<String> consumer1 = (s)-> System.out.println(":"+s.length());

        names.forEach(consumer.andThen(consumer1));

        System.out.println("===============================================");

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Consumer<Integer> c1 = System.out::print;
        Consumer<Integer> c2 = (c)-> System.out.println(":"+ (c*2));
        numbers.forEach(c1.andThen(c2));
    }
}
