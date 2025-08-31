package com.prep.interview.array;

/*
How It Works

1. Keep track of the lowest price encountered so far (minPrice).
2. For each day, calculate the profit if you sell on that day:
 */
public class BestTimeBuyAndSell {
    public static void main(String[] args) {
        // Example 1
        int[] prices1 = {7, 1, 5, 3, 6, 4}; //-> look forward for from low to high (left to right hand side)
        System.out.println("Max Profit: " + maxProfit(prices1)); // Output: 7

        // Example 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit: " + maxProfit(prices2)); // Output: 0
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0; // No profit can be made
        int maxProfit = 0; // Max profit found
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                maxProfit += (prices[i] - prices[i - 1]);
            }
        }
        return maxProfit;
    }
}
