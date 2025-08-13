package com.prep.interview.string;

public class RemoveAstrik {
    public static void main(String[] args) {
        String str = "sdf*ghf*bmd";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*')
                continue;
            sb.append(str.charAt(i));
        }
        System.out.println(sb);
    }
}
