package com.prep.interview.igGrouop;

public class SumOfString {
    static void main() {
        String str = "59";
        String str1 = "23";

        System.out.println(STR."Sum: \{addStrings(str, str1)}");//82
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int a = num1.charAt(i) - '0' == 0 ? 0 : num1.charAt(i) - '0';
            int b = num1.charAt(j) - '0' == 0 ? 0 : num2.charAt(j) - '0';
            int sum = a + b + carry;
            carry = sum / 10;
            result.append(sum % 10);
            i--;
            j--;
        }
        return result.reverse().toString();
    }
}
