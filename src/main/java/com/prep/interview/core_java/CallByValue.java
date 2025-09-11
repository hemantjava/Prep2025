package com.prep.interview.core_java;


public class CallByValue {
    public static void main(String[] args) {

        // Step 1: Primitive Example
        int a = 15;                         // 'a' is a primitive variable, value = 15
        updatePrimitive(a);                 // Passes a copy of 'a' (15) to the method
        System.out.println(a);              // Prints 15, because primitive values are not changed

        // Step 2: Mutable Object Example
        StringBuilder name = new StringBuilder("Hemant");
        // 'name' refers to a StringBuilder object in heap with content "Hemant"
        updateReference(name);              // Passes the reference (address) to the method
        System.out.println(name);           // Prints "Hemant sahu", because StringBuilder is mutable

        // Step 3: Immutable Object Example
        String nameStr = "Hemant";          // 'nameStr' refers to a String object in the String Pool
        updateImmutableString(nameStr);     // A new String is created inside method,
        // but 'nameStr' in main is not updated
        System.out.println(nameStr);        // Prints "Hemant", unchanged
    }

    // Case 1: Primitive types
    private static void updatePrimitive(int a) {
        a = 20; // Only the local copy 'a' is changed, original variable in main is unaffected
    }

    // Case 2: Mutable objects
    private static void updateReference(StringBuilder name) {
        name.append(" sahu"); // The SAME StringBuilder object is modified,
        // so changes are visible outside the method
    }

    // Case 3: Immutable objects
    private static void updateImmutableString(String nameStr) {
        nameStr = nameStr + " sahu"; // A NEW String object is created ("Hemant sahu")
        // but the reference 'nameStr' inside main is still pointing to "Hemant"
        // So the change is not reflected outside this method
    }
}
