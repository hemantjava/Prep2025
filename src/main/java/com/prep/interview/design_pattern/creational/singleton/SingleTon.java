package com.prep.interview.design_pattern.creational.singleton;

public final class SingleTon {

    private static SingleTon single;

    private SingleTon() {

    }

    public static SingleTon getInstance() {
        if (single == null) {
            single = new SingleTon();
        }
        return single;
    }
}