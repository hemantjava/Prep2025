package com.prep.interview.design_pattern.behavioural.chain_of_responsibility.chain_of_responsibility;

public class Log {

    public void getLogger(String level, String message) {
        Logger debug = new DebugLogger();
        Logger info = new InfoLogger();
        Logger error = new ErrorLogger();
        if (level.equalsIgnoreCase("debug")) {
            debug.write("debug: " + message);
        } else if (level.equalsIgnoreCase("info")) {
            debug.write("debug: " + message);
            info.write("info: " + message);
        } else if (level.equalsIgnoreCase("error")) {
            debug.write("debug: " + message);
            info.write("info: " + message);
            error.write("error: " + message);
        } else {
            System.out.println("invalid level");
        }
    }
}
