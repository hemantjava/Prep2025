package com.prep.interview.array;

import java.util.Arrays;
import java.util.HashSet;

public class CheckDuplicateContains {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(checkDuplicate(nums));
        System.out.println(checkDuplicate2(nums));
    }

    //TC: O(n log n)
    private static boolean checkDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    //TC: O(n) SC:(n)
    private static boolean checkDuplicate2(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))
                return true;
            seen.add(num);
        }
        return false;
    }

}
