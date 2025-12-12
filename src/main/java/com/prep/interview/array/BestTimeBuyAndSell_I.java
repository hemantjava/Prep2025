package com.prep.interview.array;

/*
How It Works
    The algorithm iterates through the price array starting from the second day.
    For each day, it checks if the price is higher than the previous day.
    If it is, it adds the difference (profit) to the total maxProfit.
    This way, it captures all upward movements in stock prices, simulating multiple buy-sell transactions.
    Finally, it returns the total accumulated profit.
 */
public class BestTimeBuyAndSell_I {
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

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
