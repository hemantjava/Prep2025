package com.prep.interview.design_pattern.solid.dip;

public class WireLessdMouse implements  Mouse{
    @Override
    public void click() {
        System.out.println("wireless mouse");
    }
}