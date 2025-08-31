package com.prep.interview.java17;

public class SwitchExpressionExample {
    public static void main(String[] args) {
        System.out.println(getDatType("Monday"));
        System.out.println(getDatType("Sunday"));
        System.out.println(getDatType("kgs"));
    }


    //Lambda expression (->) in switch
    private static String getDatType(String  day){
        return switch (day){
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "WeekDay";
            case "Saturday", "Sunday" -> "WeekEnd";
            default -> "Invalid Day";// compile time check default is missing or not
        };
    }

}
