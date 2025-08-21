package com.prep.interview.design_pattern.behavioural.chain_of_responsibility.chain_of_responsibility;

public class ErrorLogger implements Logger {


    @Override
    public void write(String message) {
        error(message);
    }

    public void error(String message) {
        System.out.println("ERROR: " + message);
    }
}