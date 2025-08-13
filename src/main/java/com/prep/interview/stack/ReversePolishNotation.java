package com.prep.interview.stack;


import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] expression = {"3", "4", "2", "*", "+"}; // 3 + (4*2)
        System.out.println(evaluateRPN(expression)); // Output: 11
    }


    public static int evaluateRPN(String[] str) {
        Stack<Integer> stack = new Stack<>(); // push integer value only

        for (String val : str) {
            if (isOperator(val)) {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                stack.push(solve(operand2, operand1, val)); // operand2 is be the 1st -> 7/3 -> 3,/,7 -> 7/3
            } else {
                stack.push(Integer.parseInt(val)); // convert into integer and push
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String val) {
        return "+-*/".contains(val);
    }

    private static int solve(int operand2, int operand1, String val) {
        return switch (val) {
            case "+" -> (operand2 + operand1);  //2nd operand then 1st operand
            case "-" -> (operand2 - operand1);
            case "/" -> (operand2 / operand1);
            case "*" -> (operand2 * operand1);

            default -> throw new IllegalStateException("Unexpected value: " + val);
        };
    }
}
