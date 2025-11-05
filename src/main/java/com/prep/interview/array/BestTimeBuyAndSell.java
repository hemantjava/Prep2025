package com.prep.interview.array;

/*
How It Works

1. Keep track of the lowest price encountered so far (minPrice).
2. For each day, calculate the profit if you sell on that day:
 */
public class BestTimeBuyAndSell {
    public static void main(String[] args) {
        // AtomicCounterWithLock 1
        int[] prices1 = {7, 1, 5, 3, 6, 4}; //-> look forward for from low to high (left to right hand side)
        System.out.println("Max Profit: " + maxProfit(prices1)); // Output: 7

        // AtomicCounterWithLock 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit: " + maxProfit(prices2)); // Output: 0
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0; // No profit can be made

        int maxProfit = 0;
        int min = prices[0];

        for (int v : prices) {
            min = Math.min(min, v);
            maxProfit = Math.max(maxProfit, v - min);
        }
        return maxProfit;
    }
}
