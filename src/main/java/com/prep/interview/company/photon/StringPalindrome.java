package com.prep.interview.company.photon;

public class StringPalindrome {
    public static void main(String[] args) {

        String str = "madam";
        int left = 0;
        int right = str.length() -1;
        System.out.println(check(str,left,right));

    }

    //using recursion
    private static boolean check(String str, int left, int right) {
        if (left >= right)
            return true;
        if (str.charAt(left) != str.charAt(right))
            return false;
        return check(str,left + 1, right - 1);

    }


}
