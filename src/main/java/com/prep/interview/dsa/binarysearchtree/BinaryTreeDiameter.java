package com.prep.interview.dsa.binarysearchtree;


import com.prep.interview.dsa.binarysearchtree.recursion.TreeNode;

public class BinaryTreeDiameter {

    // Global variable to track the maximum diameter found so far
    private int diameter = 0;

    /**
     * The diameter is the length of the longest path between any two nodes in the tree.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);  // start recursive depth calculation
        return diameter;
    }

    /**
     * Helper function to calculate the height of a subtree.
     * While calculating height, it also updates the diameter.
     *
     * @param node current root of the subtree
     * @return height of the subtree rooted at 'node'
     */
    private int calculateHeight(TreeNode node) {
        // Base case: null node has height 0
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of left and right subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // The longest path passing through this node = leftHeight + rightHeight
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the subtree = max(left, right) + 1 (counting current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
