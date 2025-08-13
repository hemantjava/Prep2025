## Java Memory model or area

-> in Java, the JVM divides memory into different areas, each serving a specific purpose for storing data, objects, 
and execution state.

* 1. Method Area (a.k.a. Class Area)
   * Purpose: Stores class-level data shared among all threads:
   * Class metadata (name, modifiers, superclass info)
   * Static variables, Method bytecode, Runtime constant pool
   * Lifetime: Exists for the entire JVM run.
   * Location: Part of the heap in modern JVMs (Metaspace in Java 8+ replaces PermGen).

* 2. Heap Area
  * Purpose: Stores objects and their instance variables.
  * Shared: Yes — all threads share the heap.
-> Divided into:
  * Young Generation (Eden + Survivor spaces) → for short-lived objects.
  * Old Generation → for long-lived objects.
  * Metaspace (Java 8+) → for class metadata (was in PermGen before).
  * Garbage Collected: Yes.

* 3. Stack Area
   * Purpose: Each thread has its own stack to store:
   * Method call frames, Local variables, Partial results
   * LIFO (Last In First Out) structure.
   * Lifetime: Tied to the thread's lifetime.
   * Error: StackOverflowError if recursion is too deep.

* 4. Program Counter (PC) Register
   * Purpose: Each thread has its own PC register storing the address of the currently executing JVM instruction.
              For native methods, the value is undefined.

* 5. Native Method Stack
   * Purpose: Stores information for native (non-Java) methods — usually C/C++.
   * Works with JNI (Java Native Interface).

![img_1.png](..%2Fimages%2Fcore%20java%2Fimg_1.png)
![img_2.png](..%2Fimages%2Fcore%20java%2Fimg_2.png)