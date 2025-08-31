package com.prep.interview.leetCode;

public class MaximumSubarray {
    /**
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     */
    static void main() {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(6 == maxSubArray(nums));//true
    }

    private static int maxSubArray(int[] nums) {
        int maxSum = nums[0]; //result
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], nums[i] + currentSum);
            maxSum = Math.max(currentSum, maxSum); // swapping if value is max value
        }
        return maxSum;
    }
}
