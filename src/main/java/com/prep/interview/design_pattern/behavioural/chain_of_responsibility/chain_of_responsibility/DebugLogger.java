package com.prep.interview.design_pattern.behavioural.chain_of_responsibility.chain_of_responsibility;

public class DebugLogger implements Logger {
    @Override
    public void write(String message) {
        debug(message);
    }

    public void debug(String message) {
        System.out.println("DEBUG: " + message);
    }
}
