package com.prep.interview.array;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26]; // for lowercase a-z (0-25)
        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']++;

        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (count[c - 'a'] == 0)
                return false;
            count[c - 'a']--;
        }
        return true;
    }

    public static void main(String[] args) {
        // ransomNote = "a", magazine = "b"
        //ransomNote = "aa", magazine = "ab"
        //ransomNote = "aa", magazine = "aab"
        System.out.println(canConstruct("a", "b"));//false
        System.out.println(canConstruct("aa", "ab"));//false
        System.out.println(canConstruct("aa", "aab"));//false
    }
}