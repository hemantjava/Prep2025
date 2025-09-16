package com.prep.interview.company.xebia;

import java.util.*;

public class MergeTimeSlotsHashMap {
    public static void main(String[] args) {
        // Put slots into a map (start -> end)
        Map<Integer, Integer> slots = new HashMap<>();
        slots.put(6, 8);
        slots.put(8, 10);
        slots.put(10, 13); // 1 PM
        slots.put(15, 18); // 3-6 PM

        // Sort the keys because HashMap is unordered
        List<Integer> starts = new ArrayList<>(slots.keySet());
        Collections.sort(starts);

        List<int[]> merged = new ArrayList<>();

        //1st slot start and end
        int start = starts.get(0);
        int end = slots.get(start);

        for (int i = 1; i < starts.size(); i++) {
            int nextStart = starts.get(i);
            int nextEnd = slots.get(nextStart);

            if (nextStart <= end) {
                // Merge into current
                end = Math.max(end, nextEnd);
            } else {
                // Store finished
                merged.add(new int[]{start, end});
                start = nextStart;
                end = nextEnd;
            }
        }
        // Add last one
        merged.add(new int[]{start, end});

        // Print result
        for (int[] m : merged) {
            System.out.println(m[0] + " - " + m[1]);
        }
    }
}

