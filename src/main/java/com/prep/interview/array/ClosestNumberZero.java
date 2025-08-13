package com.prep.interview.array;

public class ClosestNumberZero {
    public static void main(String[] args) {
        int[] numbers = {5, 12, 8, 7, 9, 6,-5, -6};

        System.out.println(findClosestNumToZero(numbers));

    }

    private static int findClosestNumToZero(int[] array) {
      int closestNumber = array[0]; // return number either signed or unsigned, So not used here abs
      int temp = Math.abs(array[0]);
              for (int i = 1; i < array.length; i++){
                 int abs = Math.abs(array[i]);
                  if (temp > abs){
                      closestNumber = array[i];
                      temp = abs;
                  }
              }

     return closestNumber;
    }
}
