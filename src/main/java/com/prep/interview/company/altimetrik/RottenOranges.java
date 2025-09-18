package com.prep.interview.company.altimetrik;

import java.util.*;
import java.util.LinkedList;

public class RottenOranges {

    static class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        int freshOranges = 0;

        // Step 1: Add all initial rotten oranges to the queue, and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        // 4 directional movements (up, down, left, right)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        // Step 2: Perform BFS
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            minutes++;  // Each level is 1 minute

            for (int i = 0; i < size; i++) {
                Pair rotten = queue.poll();

                for (int[] dir : directions) {
                    int newRow = rotten.row + dir[0];
                    int newCol = rotten.col + dir[1];

                    // Skip out-of-bounds or non-fresh cells
                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols)
                        continue;

                    if (grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;  // Fresh orange becomes rotten
                        freshOranges--;           // Decrease fresh count
                        queue.offer(new Pair(newRow, newCol));  // Add to queue
                    }
                }
            }
        }

        // Step 3: Check if any fresh orange remains
        return freshOranges == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };

        System.out.println("Minimum minutes to rot all oranges: " + orangesRotting(grid));//4
        System.out.println("Minimum minutes to rot all oranges: " + orangesRotting(grid2));//4
    }
}
