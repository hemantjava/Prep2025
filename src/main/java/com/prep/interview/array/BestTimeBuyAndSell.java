package com.prep.interview.array;

/*
How It Works

1. Keep track of the lowest price encountered so far (minPrice).
2. For each day, calculate the profit if you sell on that day:
3. profit = currentPrice - minPrice.
4. Update the maxProfit if this profit is higher.
5. At the end, maxProfit will have the best possible profit.
 */
public class BestTimeBuyAndSell {
    public static void main(String[] args) {
        // Example 1
        int[] prices1 = {7, 1, 5, 3, 6, 4}; //-> look forward for from low to high (left to right hand side)
        System.out.println("Max Profit: " + maxProfit(prices1)); // Output: 5

        // Example 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit: " + maxProfit(prices2)); // Output: 0
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0; // No profit can be made
        int minPrice = Integer.MAX_VALUE; // Lowest price seen so far
        int maxProfit = 0; // Max profit found

        for (int price : prices) {
            // Update the minimum price
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if sold today
            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
}
