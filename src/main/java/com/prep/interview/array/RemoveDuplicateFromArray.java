package com.prep.interview.array;

import java.util.*;

public class RemoveDuplicateFromArray {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 7, 6, 5, 2, 3, 4, 5};
        System.out.println(Arrays.toString(remove1(array)));
        System.out.println(Arrays.toString(remove2(array)));
        System.out.println(Arrays.toString(remove3(array)));
        int[] array2 = {1, 2, 2, 4, 4, 5, 6, 7, 7, 8, 9, 9};
        System.out.println(Arrays.toString(remove4(array2)));
    }

    //using Stream api
    private static int[] remove1(int[] array) {
        //IntStream.of(array).distinct().toArray();
        return Arrays.stream(array).distinct().toArray();
    }

    private static int[] remove2(int[] array) {

        //Set<Integer> set = new HashSet<>();
        // Using LinkedHashSet (Order preserved)
        Set<Integer> set = new LinkedHashSet<>();
        for (int i : array) {
            set.add(i);
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    //Using List
    private static int[] remove3(int[] array) {
        List<Integer> rs = new ArrayList<>();
        for (int i : array) {
            if (!rs.contains(i)) {
                rs.add(i);
            }
        }
        int[] newArray = new int[rs.size()];
        for (int i = 0; i < rs.size(); i++) {
            newArray[i] = rs.get(i);
        }
        return newArray;
    }

    private static int[] remove4(int[] array) {

        int n = removeDuplicates(array);

        return array;

    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // slow pointer
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j]; // overwrite duplicates from 2nd position
            }
        }
        return i + 1; // new length
    }
}
