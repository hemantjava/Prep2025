package com.prep.interview.immutable;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Q)what is Immutable class and how to create Immutable class?
 * <p>
 * Immutable class in java means that once an object is created, we cannot change its content. In Java,
 * all the wrapper classes (like Integer, Boolean, Byte, Short) and String class is immutable.
 * <p>
 * To create a custom immutable class in Java, we need to follow these steps:
 * a)Declare the class as final to prevent inheritance and overriding of methods.
 * b)Declare all instance variables as private and final to ensure they cannot be modified once assigned.
 * c)Initialize all fields through a constructor and do not provide any setter methods.
 * d)If necessary, provide getter methods to access the values of the instance variables.
 * e)For mutable fields or collections, create defensive copies during assignment.
 * f)Ensure methods that return collections or mutable fields unmodifiable.
 * g)Perform cloning of objects in the getter methods to return a copy rather than returning the actual object reference.
 */
public final class ImmutableClass {
    private final int intValue;
    private final String stringValue;
    private final List<Integer> listValue;
    private final Map<String, Integer> mapValue;
    private final Address addressValue;
    private final Date dateValue;

    public ImmutableClass(int intValue, String stringValue, List<Integer> listValue,
                          Map<String, Integer> mapValue, Address addressValue, Date dateValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.listValue = List.copyOf(listValue); // copyOf method returns immutable list
        this.mapValue = Map.copyOf(mapValue);//copyOf method returns immutable map
        this.addressValue = addressValue;
        this.dateValue = dateValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public List<Integer> getListValue() {
        return listValue; //new ArrayList(listValue);
    }

    public Map<String, Integer> getMapValue() {
        return mapValue;// new HashMap(mapValue);
    }

    public Address getaddress() {
        return new Address(addressValue.getCity(), addressValue.getCountry());
    }

    public Date getDateValue() {
        return (Date) dateValue.clone();
    }
}