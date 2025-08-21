package com.prep.interview.design_pattern.structural.facade;

public class NonVegRestaurant implements Hotel {

    public Menus getMenus()
    {
        Menus nv = new Menus("",0);
        return nv;
    }
}