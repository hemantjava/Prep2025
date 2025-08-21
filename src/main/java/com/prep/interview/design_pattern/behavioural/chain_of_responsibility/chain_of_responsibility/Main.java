package com.prep.interview.design_pattern.behavioural.chain_of_responsibility.chain_of_responsibility;

// Client
public class Main {


    public static void main(String[] args) {

        Log  log = new Log();
        System.out.println("============DEBUG===================");
        log.getLogger("DEBUG", "Hello World");
        System.out.println("============INFO===================");
        log.getLogger("INFO", "Hello World");
        System.out.println("=============ERROR=================");
        log.getLogger("ERROR", "Hello World");

    }
}