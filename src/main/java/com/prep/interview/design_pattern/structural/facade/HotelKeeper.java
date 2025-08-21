package com.prep.interview.design_pattern.structural.facade;

public interface HotelKeeper {


    public Menus getVegMenu();
    public Menus getNonVegMenu();
    public Menus getVegNonMenu();

}