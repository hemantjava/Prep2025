package com.prep.interview.string;

public class NumChar {
    //write a program in java input  AAAAABBCCAA
//    out put 5A2B2C2A
    public static void main(String[] args) {
        String str = "AAAAABBCCAA";
        System.out.println(encode(str));
    }

    public static String encode(String inputString) {
        if (inputString == null || inputString.isBlank())
            return "";
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= inputString.length(); i++) {
            if ( i < inputString.length() && inputString.charAt(i) == inputString.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(inputString.charAt(i - 1));
                count = 1;
            }
        }
      //  sb.append(count).append(inputString.charAt(inputString.length() - 1));


        return sb.toString();
    }

}
