package com.prep.interview.design_pattern.structural.adaptor.example1.adapter;


import com.prep.interview.design_pattern.structural.adaptor.example1.adaptee.WeightInPound;

public class WeightInKgImpl implements WeightInKg {

    private final WeightInPound weightInPound; //has a

    public WeightInKgImpl(WeightInPound weightInPound){
        this.weightInPound = weightInPound;
    }
    @Override
    public double getWeightInKg() {
        return weightInPound.getWeightInPounds()* 0.456;
    }
}
