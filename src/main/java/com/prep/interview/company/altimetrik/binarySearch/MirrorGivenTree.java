package com.prep.interview.company.altimetrik.binarySearch;

public class MirrorGivenTree {
    public void main(String[] args) {
        // Sample tree
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);

        BinaryTree<Integer> treeMirror = new BinaryTree<>();

        System.out.println("Original Tree In-order:");
        treeMirror.inOrderTraversal(root);
        System.out.println();

        treeMirror.mirror(root);

        System.out.println("Mirrored Tree In-order:");
        treeMirror.inOrderTraversal(root);
    }
}
