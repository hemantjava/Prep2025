package com.prep.interview.java17;

public class InstanceofPattern {
    public static void main(String[] args) {
        Object obj = "Hello World";

        //Old way
        if (obj instanceof String) {
            String s = (String) obj; // redundant cast
            System.out.println(s.toLowerCase());
        }

        // With pattern matching,
        if (obj instanceof String s) {  // pattern variable 's'
            System.out.println("Length: " + s.length()); // directly use s
        }
    }
}
