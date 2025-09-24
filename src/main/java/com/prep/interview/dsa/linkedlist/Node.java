package com.prep.interview.dsa.linkedlist;

public class Node<T> {
     public T data;  // to hold data or value
     public Node<T> next; //to next node pointer

    public Node(T data) {
        this.data = data;
    }
    public Node() {

    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", nextNode=" + next +
                '}';
    }

}