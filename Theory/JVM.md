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

## MetaSpace
In Java, metadata is the descriptive information the JVM stores about a class, method, and field so it can run your program.
It includes things like class names, method signatures, field types, annotations, and the structure of the class.
Since Java 8, this metadata is stored in Metaspace, which lives in native memory instead of the heap.
Metaspace grows as needed (up to a limit) and frees metadata when a class and its class loader are unloaded.
If class loaders leak or too many classes are generated, Metaspace can fill up and cause an OutOfMemoryError: Metaspace

## Garbage Collector
In Java, the Garbage Collector (GC) is the JVM component that automatically finds and removes objects from memory that 
are no longer reachable by any active thread, freeing heap space.
### Key points:
* Purpose: Avoids manual memory management and prevents most memory leaks.
* Works on Heap: Only cleans objects on the heap (not stack or native memory).
* Generations: Modern GCs divide the heap into Young Generation (Eden + Survivor spaces) and Old Generation for efficient cleanup.
* Triggers: Runs automatically when memory is low, but we can also invoke with System.gc() (not guaranteed for cleanup).
* Types in HotSpot JVM: Serial GC, Parallel GC, CMS (deprecated), G1 GC (default since Java 9), ZGC, Shenandoah.
![img.png](..%2Fimages%2Fgrpc%2Fimg.png)

* 1. Eden Space
   * Purpose: Where all newly created objects start their life.
   * When you create an object (new keyword), it goes into Eden.
   * Eden is small and fills up quickly.
   * GC Interaction: When Eden is full, a Minor GC runs.
     Live objects are moved (copied) from Eden to a Survivor space.
      Dead (unreferenced) objects are discarded.

* 2. Survivor Spaces
   Structure: Two survivor spaces: S0 (Survivor 0) and S1 (Survivor 1).
   Only one is used at a time for holding objects; the other is empty.
   Temporarily hold objects that survived the last Minor GC.
   Each surviving object gets an age counter.
   If an object’s age exceeds a threshold (default ~15 GCs), it’s promoted to the Old Generation.

## Flow Example:
Create object → Eden ->Minor GC → Survives?
No: Freed.
Yes: Moved to S0, age count = 1.
Next Minor GC: Objects from Eden + S0 copied to S1 (age incremented).
Repeat until threshold reached → move to Old Generation.
![img_3.png](..%2Fimages%2Fcore%20java%2Fimg_3.png)