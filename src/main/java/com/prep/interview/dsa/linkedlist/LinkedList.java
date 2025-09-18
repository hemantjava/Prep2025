package com.prep.interview.dsa.linkedlist;

import lombok.Getter;

public class LinkedList<T> {

    @Getter
    private Node<T> head;
    private int length;

    public LinkedList(T t) {
        head = newNode(t);
        length++;
    }

    public LinkedList() {
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                ", length=" + length +
                '}';
    }

    public void printList() {
        if (length != 0) {
            Node<T> temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        } else {
            System.out.print("[]");
        }
        System.out.println();
    }

    //Extra auxiliary space O(n)
    private void printRecursive(Node<T> headNode) {
        if (headNode == null) {
            System.out.println();
            return;
        }
        System.out.print(headNode.data + " ");
        printRecursive(headNode.next);
    }

    public void printRecursive() {
        printRecursive(head);
    }


    private Node<T> newNode(T t) {
        return new Node<>(t);
    }

    public void preAppend(T t) {
        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            head = newNode;
            length++;
            return;
        }
        newNode.next = head;
        head = newNode; //  new node becomes head
        length++;
    }

    public void append(T t) {
        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            head = newNode;
            length++;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    //get indexed node
    public Node<T> getNode(int index) {
        if (index < 0 || index >= length) return null;
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next; // temp indexed time
        }
        return temp;
    }

    public T getData(int index) {
        if (index < 0 || index >= length) return null;
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next; // temp indexed time
        }
        return temp.data;
    }

    public boolean set(int index, T value) {  //update operation
        Node<T> temp = getNode(index); //get index value
        if (temp != null) {
            temp.data = value; //updating indexed data
            return true;
        }
        return false;
    }

    public Node<T> deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = head;
        head = head.next;
        length--;
        return temp;
    }

    public Node<T> deleteLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = getNode(size() - 2);
        Node<T> delNode = temp.next;
        temp.next = null;
        length--;
        return delNode;
    }

    void reverse() {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            Node<T>  next = current.next;
            current.next = previous;
            previous = current; //head,current 2nd
            current = next;
        }

        head = previous;
    }

    private Node reverseRecursion(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node reversedList = reverseRecursion(node.next);
        node.next.next = node;
        node.next = null;

        return reversedList;
    }

    void reverseRecursion() {
        head = reverseRecursion(head);
    }

    public int searchIndex(T data) {
        Node<T> temp = head;
        int index = 0;
        while (temp.next != null) {
            if (temp.data == data)
                return index;
            index++;
            temp = temp.next;
        }
        return -1;
    }
    public T findMiddleNode(){
        Node<T> temp = head;
        Node<T> slow = temp;
        Node<T> fast = temp;
        while (fast !=null && fast.next !=null){
             slow = slow.next;
             fast = fast.next.next;
        }
        return slow.data;
    }
    // Recursive function to merge two sorted lists
    public Node<T> mergeTwoLists(Node<T>  list1, Node<T>  list2) {
        // Base cases:
        if (list1 == null)
            return list2; // If list1 is empty, return list2
        if (list2 == null)
            return list1; // If list2 is empty, return list1

        // Recursive case:
        if ((int)list1.data <= (int)list2.data) {
            // list1's value is smaller, keep list1 node
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // list2's value is smaller, keep list2 node
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public boolean hasCycle(Node<T> head) {
        Node<T> slow=head;
        Node<T> fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }


}
