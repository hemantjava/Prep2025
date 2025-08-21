### A memory leak in Java
- happens when objects are no longer used but still referenced, preventing the Garbage Collector (GC)from reclaiming them.
- Here are several ways to fix or avoid memory leaks in Java:
#### 1. Remove Unused Object References
- If objects are kept in memory unnecessarily (e.g., in collections), clear them when not needed.
```java
List<String> cache = new ArrayList<>();

// After using cache
cache.clear();  // Helps GC reclaim memory

```
### 3. Close Resources Properly
- Unclosed resources (DB connections, file streams, sockets) cause memory leaks.
- Use try-with-resources:
```java
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(reader.readLine());
} // reader auto-closed

```
### 4. Avoid Static References Holding Large Objects
- Static fields live until the class is unloaded (often till JVM shutdown).
```java
public class MemoryLeak {
    private static List<String> cache = new ArrayList<>(); // Bad: stays forever
}

```
### 7. Tune Thread Usage
- Unclosed threads (e.g., in Executors) can leak memory.
- Always shutdown thread pools:
```java
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.shutdown();
```
###  8. Use Profiling & Leak Detection Tools
- Eclipse MAT (Memory Analyzer Tool)
- VisualVM
- JProfiler / YourKit
- They help detect leaks, e.g., objects that are retained but unused.

### 10. Prefer Virtual Threads (Java 21+) for Concurrency
- Platform threads can retain large stacks, 
but virtual threads are much lighter and don’t typically leak memory due to blocked threads.

### ✅ Summary:
* Remove unused references
* Use weak references where possible
* Close resources properly
* Avoid static & inner-class leaks
* Clean up listeners and threads
* Use profiling tools to detect leaks