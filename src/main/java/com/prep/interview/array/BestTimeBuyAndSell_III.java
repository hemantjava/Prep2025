package com.prep.interview.array;

/*
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
However, you can sell and buy the stock multiple times on the same day, ensuring you never hold than one share of the stock.
 */
public class BestTimeBuyAndSell_III {
    public static void main(String[] args) {
        // AtomicCounterWithLock 1
        int[] prices1 = {3, 3, 5, 0, 0, 3, 1, 4}; //-> look forward for from low to high (left to right hand side)
        System.out.println("Max Profit: " + maxProfit(prices1)); // Output: 6

        // AtomicCounterWithLock 2
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Max Profit: " + maxProfit(prices2)); // Output: 4

        // AtomicCounterWithLock 2
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit: " + maxProfit(prices3)); // Output: 0
    }

    // AtomicCounterWithLock 1
    public static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price); // first buy
            sell1 = Math.max(sell1, buy1 + price); // first sell
            buy2 = Math.max(buy2, sell1 - price); // second buy
            sell2 = Math.max(sell2, buy2 + price); // second sell
        }
        return sell2;
    }
}
