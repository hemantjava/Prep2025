package com.prep.interview.design_pattern.solid.dip;

public class WiredMouse implements  Mouse{
    @Override
    public void click() {
        System.out.println("wired mouse");
    }
}
