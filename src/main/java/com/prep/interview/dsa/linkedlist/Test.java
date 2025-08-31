package com.prep.interview.dsa.linkedlist;

import com.prep.interview.dsa.linkedlist.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList(1);
        myList.append(2);
        myList.append(3);
        myList.append(4);
        myList.append(5);
        myList.printList();
        System.out.println(myList.findMiddleNode());
    }


}
