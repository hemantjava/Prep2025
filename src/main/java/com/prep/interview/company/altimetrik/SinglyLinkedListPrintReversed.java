package com.prep.interview.company.altimetrik;

import java.util.Stack;

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
       // this.next = null;
    }
}

class LinkedList<T> {
    Node<T> head;

    // Add node at the end
    public void append(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(data);
    }

    // Print list in reverse using recursion

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursive call stack
     * @param node 'head'
     */
    public void printReverse(Node<T> node) {
        if (node == null) {
            return;
        }

        printReverse(node.next);
        System.out.print(node.data + " ");
    }

    // Print list in reverse using a stack
    public void printReverse() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = head;

        // Push all nodes onto the stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // Pop and print nodes from the stack
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
        }
    }

}

public class SinglyLinkedListPrintReversed {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);

        System.out.print("Linked List in reverse: ");
        list.printReverse(list.head);
    }
}
