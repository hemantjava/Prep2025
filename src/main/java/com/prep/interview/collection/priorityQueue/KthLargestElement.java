package com.prep.interview.collection.priorityQueue;

import java.util.PriorityQueue;

/**
 * How It Works:
 * Maintain a Min-Heap (size = k).
 * Add elements one by one.
 * Once the heap exceeds size k, remove the smallest element.
 * At the end, the heap root (peek()) is the k-th largest element.
 * Time: O(n log k)
 * Space: O(k) (heap stores k elements)
 */
public class KthLargestElement {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // by default mean heap minimum elements at top

        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove smallest in the heap from top to maintain heap k size
            }
        }
        return minHeap.isEmpty() ? -1 : minHeap.peek();  // The root of the min-heap is the k-th largest element
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int kthLargest = findKthLargest(nums, k);
        System.out.println(k + "-th largest element is: " + kthLargest);  // Output: 5
    }

}
