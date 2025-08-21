package com.prep.interview.design_pattern.structural.facade;

public class Client {
    public static void main(String[] args) {
        HotelKeeper keeper = new HotelKeeperImpl();
        System.out.println(keeper.getVegMenu().getItem());
    }
}
