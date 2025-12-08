package com.prep.interview.collection.priorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    public static int findKthSmallest(int[] nums, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());// Max heap

        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();  // Remove the largest in the heap from top to maintain k size
            }
        }
        // The root of the max-heap is the k-th smallest element
        return maxHeap.isEmpty() ? -1 : maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;

        int kthSmallest = findKthSmallest(nums, k);
        System.out.println(k + "-th smallest element is: " + kthSmallest);  // Output: 7
    }
}

