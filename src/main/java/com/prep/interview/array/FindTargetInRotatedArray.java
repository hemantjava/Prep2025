package com.prep.interview.array;

public class FindTargetInRotatedArray {
    static void main() {

    }

    private static int find(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + right;// left+ (right-left)/2
            if (arr[mid] == target)
                return mid;
            if (arr[left] <= arr[mid]) { //left to right is sorted
                if (arr[left] <= target && target < arr[mid])
                    right = mid - 1;//discarded right
                else
                    left = mid + 1;

            } else {
                if (arr[mid] < target && target <= arr[right])
                    left = mid + 1;   //discarded left
                else
                    right = mid - 1;
            }

        }
        return -1;
    }
}
