package com.prep.interview.company.altimetrik.binarySearch;


import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> {

    public TreeNode<T> mirror(TreeNode<T> root) {
        if (root == null) {
            return null;
        }

        // Swap left and right subtrees
        TreeNode<T> temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);

        return root;
    }

    // Helper method to print In-order traversal of the tree
    void inOrderTraversal(TreeNode<T> root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }

    // Result list to store nodes at distance k
    private final List<Integer> result = new ArrayList<>();

    // Main method
    public List<Integer> findNodesAtDistanceK(TreeNode<T> root, TreeNode<T> target, int k) {
        findDistance(root, target, k);
        return result;
    }

    // Helper function to find distance from root to target
    private int findDistance(TreeNode<T> node, TreeNode <T> target, int k) {
        if (node == null) return -1;

        if (node == target) {
            // Collect nodes downward from target
            collectNodesAtDistanceK(node, k);
            return 0;
        }

        int leftDistance = findDistance(node.left, target, k);
        if (leftDistance != -1) {
            // Current node is at distance leftDistance + 1 from target
            if (leftDistance + 1 == k) {
                result.add(node.val);
            } else {
                collectNodesAtDistanceK(node.right, k - leftDistance - 2);
            }
            return leftDistance + 1;
        }

        int rightDistance = findDistance(node.right, target, k);
        if (rightDistance != -1) {
            if (rightDistance + 1 == k) {
                result.add(node.val);
            } else {
                collectNodesAtDistanceK(node.left, k - rightDistance - 2);
            }
            return rightDistance + 1;
        }

        return -1;
    }

    // Helper to collect nodes downward at distance k
    private void collectNodesAtDistanceK(TreeNode<T> node, int k) {
        if (node == null || k < 0) return;

        if (k == 0) {
            result.add(node.val);
            return;
        }

        collectNodesAtDistanceK(node.left, k - 1);
        collectNodesAtDistanceK(node.right, k - 1);
    }

}
