package com.prep.interview.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitTheArrayInChunks {
    public static <T> List<List<T>> splitList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int chunkSize = 2;
        List<List<Integer>> result = splitList(numbers, chunkSize);

        System.out.println("Original List: " + numbers);
        System.out.println("Chunks: " + result);
    }
}
