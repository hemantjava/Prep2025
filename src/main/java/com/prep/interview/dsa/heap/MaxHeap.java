package com.prep.interview.dsa.heap;

// MaxHeap.java
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with given capacity
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Get parent index
    private int parent(int index) { return (index - 1) / 2; }

    // Get left child index
    private int leftChild(int index) { return 2 * index + 1; }

    // Get right child index
    private int rightChild(int index) { return 2 * index + 2; }

    // Swap elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Insert a new element into the heap
    public void insert(int value) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    // Maintain heap property after insertion (bubble-up)
    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] < heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // Extract (remove) the maximum element (root)
    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    // Maintain heap property after deletion (bubble-down)
    private void heapifyDown(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] > heap[largest])
            largest = left;
        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    // Print heap as array
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(30);

        System.out.print("Max Heap: ");
        maxHeap.printHeap();

        System.out.println("Extracted Max: " + maxHeap.extractMax());
        System.out.print("After Extraction: ");
        maxHeap.printHeap();
    }
}
