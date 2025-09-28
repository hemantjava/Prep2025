### 1. Virtual Threads (Finalized – JEP 444)
* Lightweight threads managed by JVM.
* Scale concurrent apps easily (millions of threads).
* Improves server apps (Spring, Tomcat, Netty).
  👉 Alternative to using thread pools.
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

//OR
public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual().start(() -> {
            System.out.println("Running in virtual thread: " + Thread.currentThread());
        });

        thread.join(); // wait for completion
    }
}

```
### 2. Sequenced Collections (Finalized – JEP 431)
* New interface SequencedCollection, SequencedSet, SequencedMap.
* Provides first/last element access in consistent order.
```java
SequencedCollection<String> list = new ArrayList<>();
list.addFirst("A");
list.addLast("B");

```
### 3. Record Patterns (Finalized – JEP 440)
* Pattern matching with record deconstruction in switch/if.
```java
record Point(int x, int y) {}
Object obj = new Point(10, 20);

if (obj instanceof Point(int x, int y)) {
System.out.println(x + ", " + y);
}
```
### 4. Pattern Matching for Switch (Finalized – JEP 441)
* Use switch with types + patterns.
* Reduces boilerplate instanceof checks.

```java
static String formatter(Object obj) {
return switch (obj) {
case Integer i -> "int: " + i;
case String s -> "str: " + s;
case null     -> "null";
default       -> "unknown";
};
}

```
### 8. Foreign Function & Memory API (Finalized – JEP 442)
* Interop with native code (C libraries).
* Alternative to JNI with safe memory management.
### 9. Key Encapsulation Mechanism (KEM) API (JEP 452)
* New crypto API for modern key exchange mechanisms.
* Supports post-quantum cryptography (PQC).

### 10. Generational Z Garbage Collector (ZGC )
* Z Garbage Collector now generational → reduces pause times.
* Better memory performance for large heaps.

### ✅ Summary:
Java 21 makes concurrency (virtual threads, scoped values) easier, language syntax (patterns, templates, unnamed classes) 
cleaner, and performance/security stronger (ZGC, FFM, KEM).
SequencedCollection, SequencedSet, SequencedMap) that provide consistent
Good question 👍 — let’s break it down properly.

In **Java 21**, three new interfaces were introduced / enhanced in the collections framework:

* **`SequencedCollection<E>`**
* **`SequencedSet<E>`**
* **`SequencedMap<K,V>`**

They bring **first/last/reversed ordering APIs** that were missing before.

---

# 🔹 1. `SequencedCollection<E>`

Implemented by: `List`, `Deque`, `LinkedHashSet` (since it’s also a set of ordered elements).

### **New methods in Java 21**:

```java
E getFirst();
E getLast();

E removeFirst();   // throws exception if empty
E removeLast();    // throws exception if empty

E pollFirst();     // returns null if empty
E pollLast();      // returns null if empty

void addFirst(E e);
void addLast(E e);

SequencedCollection<E> reversed();
```

---

# 🔹 2. `SequencedSet<E>`

Extends both **`Set<E>`** and **`SequencedCollection<E>`**.
Implemented by: `LinkedHashSet`, `SortedSet` (TreeSet).

### **Methods available** (inherits all from `SequencedCollection`):

```java
E getFirst();
E getLast();

E removeFirst();
E removeLast();

E pollFirst();
E pollLast();

void addFirst(E e);
void addLast(E e);

SequencedSet<E> reversed();
```

⚡ Difference: Ensures **uniqueness** (like a Set), while keeping order.

---

# 🔹 3. `SequencedMap<K,V>`

Implemented by: `LinkedHashMap`, `SortedMap` (TreeMap).

### **New methods in Java 21**:

```java
Map.Entry<K,V> firstEntry();
Map.Entry<K,V> lastEntry();

Map.Entry<K,V> pollFirstEntry();
Map.Entry<K,V> pollLastEntry();

V putFirst(K key, V value);
V putLast(K key, V value);

SequencedMap<K,V> reversed();
```

---

# 📌 Summary Table

| Interface               | First/Last Access      | Poll/Remove          | Add First/Last     | Reversed View | Uniqueness           | Key-Value   |
| ----------------------- | ---------------------- | -------------------- | ------------------ | ------------- | -------------------- | ----------- |
| **SequencedCollection** | ✅ getFirst/getLast     | ✅ poll/remove        | ✅ addFirst/addLast | ✅ reversed    | ❌ duplicates allowed | ❌           |
| **SequencedSet**        | ✅                      | ✅                    | ✅                  | ✅             | ✅ unique elements    | ❌           |
| **SequencedMap**        | ✅ firstEntry/lastEntry | ✅ pollFirst/pollLast | ✅ putFirst/putLast | ✅ reversed    | ✅ unique keys        | ✅ key-value |

---

👉 So, practically:

* Use **`SequencedCollection`** when you need ordered list-like behavior (playlists, queues).
* Use **`SequencedSet`** when you need **unique ordered elements** (workflows, history).
* Use **`SequencedMap`** when you need **ordered key-value pairs** (caches, registries).

---
Perfect 👍 — here are **short, interview-style answers** for the **Top 5 JDK 21 Final features**.

---

## 1. **Virtual Threads (JEP 444)**

**Interview Answer:**
Virtual threads are lightweight threads introduced in Java 21 as part of Project Loom. They drastically reduce the cost of creating and managing threads, making it possible to handle millions of concurrent tasks with simpler, blocking code instead of complex async frameworks.

---

## 2. **Record Patterns (JEP 440)**

**Interview Answer:**
Record patterns let you deconstruct record objects directly in pattern matching. This reduces boilerplate code and improves readability when working with immutable data carriers like records.

---

## 3. **Pattern Matching for `switch` (JEP 441)**

**Interview Answer:**
Pattern matching for `switch` extends the `switch` statement to support type patterns and null checks directly. It simplifies conditional logic by avoiding explicit casting and manual `instanceof` checks.

---

## 4. **Sequenced Collections (JEP 431)**

**Interview Answer:**
Sequenced collections introduce new interfaces (`SequencedCollection`, `SequencedSet`, `SequencedMap`) to provide consistent APIs for accessing the first and last elements of collections. This unifies operations across lists, sets, and maps.

---

## 5. **Key Encapsulation Mechanism (KEM) API (JEP 452)**

**Interview Answer:**
The KEM API provides a modern cryptographic mechanism for secure key exchange, supporting algorithms like X25519 and DHKEM. It’s simpler and more secure than older key agreement approaches like Diffie–Hellman.

---
