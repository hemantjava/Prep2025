package com.prep.interview.dsa.binarysearchtree.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> root, T data) {
        if (root == null)
            return new Node<>(data);
        if (data.compareTo(root.data) < 0)
            root.left = insertRecursive(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = insertRecursive(root.right, data);
        return root;
    }

    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> root, T data) {
        if (root == null)
            return false;
        if (data.compareTo(root.data) == 0)
            return true;
        else if (data.compareTo(root.data) < 0)
            return searchRecursive(root.left, data);
        return searchRecursive(root.right, data);

    }

    public void delete(T data) {
        root = deleteRecursive(root, data);
    }

    private Node<T> deleteRecursive(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = deleteRecursive(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = deleteRecursive(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleteRecursive(root.right, root.data);
        }

        return root;
    }

    private T minValue(Node<T> root) { // minValue is always left side of the tree
        Node<T> temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.data;
    }

    public void inorderTraversal() {  // always sorted form data  -> left - root - right note:- root middle print data in middle
        inorderTraversalRecursive(root);
    }

    private void inorderTraversalRecursive(Node<T> root) {
        if (root != null) {
            inorderTraversalRecursive(root.left);
            System.out.print(root.data + " ");
            inorderTraversalRecursive(root.right);
        }
    }

    public List<T> rInorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderTraversalRecursive(root, result);
        return result;
    }

    private void inorderTraversalRecursive(Node<T> root, List<T> result) {
        if (root != null) {
            inorderTraversalRecursive(root.left, result);
            result.add(root.data);
            inorderTraversalRecursive(root.right, result);
        }
    }

    public void postorderTraversal() {      // -> left - right - root note:- root end print data in end
        postorderTraversalRecursive(root);
    }

    private void postorderTraversalRecursive(Node<T> root) {
        if (root != null) {
            postorderTraversalRecursive(root.left);
            postorderTraversalRecursive(root.right);
            System.out.print(root.data + " ");
        }
    }

    public List<T> rPostorderTraversal() {
        List<T> queue = new LinkedList<>();
        postorderTraversalRecursive(root, queue);
        return queue;
    }

    private void postorderTraversalRecursive(Node<T> root, List<T> queue) {
        if (root != null) {
            postorderTraversalRecursive(root.left, queue);
            postorderTraversalRecursive(root.right, queue);
            queue.add(root.data);
        }
    }

    public void preorderTraversal() {//note:- root first print data in first
        preorderTraversalRecursive(root);
    }

    private void preorderTraversalRecursive(Node<T> root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderTraversalRecursive(root.left);
            preorderTraversalRecursive(root.right);
        }
    }

    // Preorder traversal returning array
    public List<T> rPreorderTraversal() {
        List<T> result = new ArrayList<>();
        preorderTraversalRecursive(root, result);
        return result;
    }

    private void preorderTraversalRecursive(Node<T> root, List<T> result) {
        if (root != null) {
            result.add(root.data); // root
            preorderTraversalRecursive(root.left, result);  // left
            preorderTraversalRecursive(root.right, result); // right
        }
    }


    //Imp
    public List<T> breadthFirstSearch() { //Level Order traversal
        Queue<Node<T>> queue = new LinkedList<>(); //hold node/root node
        List<T> result = new ArrayList<>();//hold data
        queue.add(root); //holding root node
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.remove();//dequeue
            result.add(currentNode.data);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return result;
    }

    //Imp
    public List<List<T>> levelOrderTraversal() {
        Queue<Node<T>> queue = new LinkedList<>();
        List<List<T>> result = new ArrayList<>();
        if (root == null) return result;
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at current level
            List<T> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node<T> currentNode = queue.remove();
                currentLevel.add(currentNode.data);

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public T minValue() {
        return minValue(root);
    }

    public T maxValue() {
        return maxValue(root);
    }

    private T maxValue(Node<T> root) {
        Node<T> temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.data;
    }

    public boolean isValidBST(Node<Integer> root) {
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);

        return IntStream.range(0, list.size() - 1)
                .allMatch(i -> list.get(i) <= (list.get(i + 1)));  // Strictly increasing
    }

    private void inOrderTraversal(Node<Integer> root, List<Integer> list) {
        if (root == null) return;
        inOrderTraversal(root.left, list);
        list.add(root.data);
        inOrderTraversal(root.right, list);
    }

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValid(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        return isValid(node.left, min, (long) node.val) && isValid(node.right, (long) node.val, max);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
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

    public TreeNode mirror(TreeNode root) {
        if (root == null) {
            return null;
        }
        // Swap left and right subtrees
       TreeNode temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);

        return root;
    }
}