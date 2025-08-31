package com.prep.interview.java21;



import java.util.*;

public class WorkflowMap {
    public static void main(String[] args) {
        SequencedMap<Integer, String> workflow = new LinkedHashMap<>();
        new LinkedList<>();

        workflow.put(1, "Validate Input");
        workflow.put(2, "Transform Data");
        workflow.put(3, "Store in DB");

        System.out.println("Forward Execution:");

        System.out.println(workflow);

        System.out.println("\nRollback Execution:");
        System.out.println(workflow.reversed());
        System.out.println(workflow.firstEntry());
        System.out.println(workflow.lastEntry());
        System.out.println(workflow.pollFirstEntry());
        System.out.println(workflow.pollLastEntry());
        workflow.putFirst(3,"Update Stove");
        System.out.println(workflow);
    }
}
