package com.prep.interview.design_pattern.solid.isp;

public class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
}
