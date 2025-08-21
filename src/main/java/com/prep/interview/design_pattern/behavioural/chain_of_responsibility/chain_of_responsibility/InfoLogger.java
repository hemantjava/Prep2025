package com.prep.interview.design_pattern.behavioural.chain_of_responsibility.chain_of_responsibility;

// Concrete Handlers
public class InfoLogger implements Logger {
    @Override
    public void write(String message) {
        info(message);
    }

    public void info(String message) {
        System.out.println("INFO: " + message);
    }
}
