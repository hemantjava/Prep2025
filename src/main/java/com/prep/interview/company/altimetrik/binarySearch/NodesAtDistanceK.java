package com.prep.interview.company.altimetrik.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class NodesAtDistanceK {


    // Example usage
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);

        BinaryTree<Integer> solution = new BinaryTree<>();
        TreeNode<Integer> target = root.left;  // Node with value 2
        int k = 2;

        List<Integer> nodesAtK = solution.findNodesAtDistanceK(root, target, k);
        System.out.println("Nodes at distance " + k + " from target " + target.val + ": " + nodesAtK);
    }
}
