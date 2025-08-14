### In Java, fail-fast and fail-safe describe how collections behave when they are modified during iteration:

Fail-Fast Collections:
* Fail-fast collections immediately throw a ConcurrentModificationException if they detect that the collection has been 
  modified (structurally) by another thread while iterating over it.
* Examples: ArrayList, HashMap, HashSet.
*  These collections use an internal modification count (modCount) to detect concurrent modifications during iteration.

Fail-Safe Collections:
* Behavior: Fail-safe collections do not throw exceptions if modified during iteration because they operate on a clone 
  or snapshot of the collection.
* CopyOnWriteArrayList, ConcurrentHashMap.
* They either work on a copy of the data or use more advanced techniques to allow safe iteration in a concurrent environment.

Imp-Q:2). Difference between ArrayLIst and LinkedList ?
**ArrayList:-->**
ArrayList is a implemented class of List.
ArrayList is a resizable or growable array.
ArrayList is the best for retrieval operation. /best for read operation.
ArrayList is the worst for insertion or deletion in the middle. /worst for write operation. because multiple shift operation internally
ArrayList implements Random Access interface.

**LinkedList:-->**
LinkedList is a implemented class of list.
LinkedList is a Doubly linkedlist.
LinkedList is the worst for retrieval operation. /worst for read operation.
linkedlist is the best for insertion or deletion in the middle. /best for write operation.
LinkedList doesn't implement Random Access interface.

Q). Collection vs Collections ?
Ans). Collection:
-Collection is an interface.
-It is used to represent a group of individual objects as a single unit.
-It contains static method, also contain abstract and default methods.

Collections:
-Collections is a utility class.  
-It defines several utility methods that are used to operate on a collection.
-It contains only static methods.
