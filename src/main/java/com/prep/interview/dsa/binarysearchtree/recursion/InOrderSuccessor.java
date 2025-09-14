package com.prep.interview.dsa.binarysearchtree.recursion;

import java.util.LinkedList;
import java.util.List;

public class InOrderSuccessor {
    static void main() {
        TreeNode root = new TreeNode(20);
        //left
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        //right
        root.right = new TreeNode(22);
        System.out.println(inOrderPrint(root));//[4, 8, 10, 12, 14, 20, 12]
        System.out.println(inOrderSuccessor(root, new TreeNode(12)).val);
    }

    // TC : O(H)->O(logN) and SC:O(1)
    public static TreeNode inOrderSuccessor(TreeNode root, TreeNode x) {
        TreeNode successor = null;
        TreeNode current = root;
        while (current != null) {
            successor = current; //20,8,12,14
            if (x.val < current.val) {
                current = current.left;//8
            } else {
                current = current.right;//12,14
            }
        }
        return successor;
    }


    private static List<Integer> inOrderPrint(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inOrderPrint(root, result);
        return result;
    }

    private static void inOrderPrint(TreeNode root, List<Integer> result) {
        if (root != null) {
            inOrderPrint(root.left, result);
            result.add(root.val);
            inOrderPrint(root.right, result);
        }

    }

}
