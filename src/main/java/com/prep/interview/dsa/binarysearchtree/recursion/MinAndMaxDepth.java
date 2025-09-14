package com.prep.interview.dsa.binarysearchtree.recursion;

public class MinAndMaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return maxDfs(root);

    }

    private int maxDfs(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE; //base case for recursion
        if (root.left == null && root.right == null) return 1;
        int left = maxDfs(root.left);
        int right = maxDfs(root.right);
        return 1 + Math.max(left, right);
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDfs(root);
    }

    private int minDfs(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE; //base case for recursion
        if (root.left == null && root.right == null) return 1;
        int left = minDfs(root.left);
        int right = minDfs(root.right);
        return 1 + Math.min(left, right);
    }

    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        MinAndMaxDepth minAndMaxDepth = new MinAndMaxDepth();
        System.out.println(minAndMaxDepth.minDepth(root));
        System.out.println(minAndMaxDepth.maxDepth(root));

    }
}


