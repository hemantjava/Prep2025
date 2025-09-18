package com.prep.interview.array;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    static void main() {
       int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
        //Output: [1, 1, 4, 2, 1, 1, 0, 0]
    }
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // store indices

        for (int i = 0; i < n; i++) {
            // While current temp is greater than temp at stack top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // days waited
            }
            stack.push(i); // push current index
        }

        // Remaining indices in stack already default 0
        return result;
    }
}
