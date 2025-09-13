package com.prep.interview.dsa.binarysearchtree.recursion;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] data = {50, 30, 70, 20, 40, 10, 60};
        for (int val : data) {
            bst.insert(val);
        }
        System.out.println("Inorder Traversal:");//ascending order
        bst.inorderTraversal();
        System.out.println("\n");
        System.out.println(bst.rInorderTraversal());
        System.out.println("\nPostorder Traversal:");
        bst.postorderTraversal();
        System.out.println("\n");
        System.out.println(bst.rPostorderTraversal());
        System.out.println("\nPreorder Traversal:");
        bst.preorderTraversal();
        System.out.println("\n");
        System.out.println(bst.rPreorderTraversal());
        System.out.println("\nminValue: " + bst.minValue());
        System.out.println("maxValue: " + bst.maxValue());
        System.out.println("search 50:" + bst.search(50));
        System.out.println(bst.isValidBST(bst.getRoot()));
        System.out.println(bst.breadthFirstSearch());
        System.out.println(bst.levelOrderTraversal());


    }
}
