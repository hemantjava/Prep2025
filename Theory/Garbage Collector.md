**Memory Areas in JVM**

The JVM memory is divided into several parts:

1. Heap Memory
   This is the runtime memory where objects are allocated. The heap is further divided into generations to optimize GC performance:

  * **Young Generation (Young Gen):**

Contains newly created objects.
Divided into:
   * Eden Space: Where most new objects are initially allocated.
   * Survivor Spaces (S0 and S1): Hold objects that survive garbage collections in the Eden space.
   * Garbage Collection: Minor GC reclaims memory by removing short-lived objects.


  * **Old Generation (Tenured Gen):**

* Stores long-lived objects that survived multiple GC cycles in the young generation.
* Garbage Collection: Major GC (or Full GC) occurs here, which is more expensive in terms of time.
MetaSpace (since Java 8):

Replaces PermGen (used in earlier Java versions).
Stores class metadata and is allocated from native (non-heap) memory.
No fixed size; grows dynamically.

**2. Non-Heap Memory**

   Includes memory for JVM internals:

Thread Stacks: Contains method call frames, local variables, and partial results for threads.
Program Counter (PC) Registers: Tracks the current instruction being executed for each thread.
Native Method Stacks: Supports native methods via the Java Native Interface (JNI).
Garbage Collection (GC) Process
Garbage collection in Java is the process of identifying and reclaiming memory occupied by objects that are no longer reachable. The JVM uses a combination of techniques to achieve this:

1. Mark-and-Sweep Algorithm
   Mark Phase: The GC identifies all reachable objects by traversing object references starting from "roots" (e.g., static fields, local variables).
   Sweep Phase: Memory occupied by unreachable objects is reclaimed.
2. Generational Collection
   Java uses generational garbage collection to optimize performance:

Minor GC: Reclaims memory from the young generation.
Major GC (Full GC): Reclaims memory from both the young and old generations.
3. Reference Types
   GC behavior depends on the type of object references:

Strong References: Prevent GC unless explicitly set to null.
Soft References: Cleared before OutOfMemoryError.
Weak References: Cleared during the next GC cycle.
Phantom References: Used for post-mortem cleanup; requires explicit reference queue.

### Garbage Collectors across Java 8, 11, 17, and 21 with their default and available options:

default Garbage Collector (GC) used across major Java versions:
•	Java 8 → Parallel GC (a throughput-oriented collector).
•	Java 11 → G1 GC (Garbage First, became the default to reduce pause times).
•	Java 17 → G1 GC (still the default, with improvements).
•	Java 21 → G1 GC (default, but ZGC and Shenandoah are fully production-ready and highly tuned for low latency).
👉 You can still choose others (-XX:+UseParallelGC, -XX:+UseZGC, -XX:+UseShenandoahGC, etc.) depending on needs.

- java Version	Default GC	Other Available GCs	:-
- Java 8 **Parallel GC** Serial, maximize work done by minimizing GC overhead	Higher pause times, not great for large heaps
- Java 11 **G1 GC** Parallel, Serial, Epsilon (no-op), ZGC (experimental)	Balanced – reduces pause times vs Parallel	
  More predictable pauses but slightly lower throughput than Parallel
- Java 17 (LTS)	**G1 GC** Parallel, Serial, ZGC (stable), Shenandoah (optional), Epsilon Low pause times with large heaps
   Good balance, ZGC/Shenandoah better for very low latency apps
- Java 21 (LTS)	**G1 GC**	Parallel, Serial, ZGC (mature), Shenandoah (mature), Epsilon Balanced by default; low-latency 
  collectors (ZGC/Shenandoah) available	G1 is fine for most apps, but ZGC/Shenandoah best for real-time, large heap, low latency workloads

👉 Rule of thumb:
•	Use Parallel GC → If throughput matters most (batch/ETL jobs).
•	Use G1 (default) → If balanced throughput & latency is fine (most enterprise apps).
•	Use ZGC/Shenandoah → If low latency is critical (financial, trading, gaming, real-time apps).

| **GC**          | **Default Version**                      | **Pause Time**                             | **Throughput** | **Memory Usage**           | **Use Case / Pros**                                                | **Cons / Limitations**                                   |
| --------------- | ---------------------------------------- | ------------------------------------------ | -------------- | -------------------------- | ------------------------------------------------------------------ | -------------------------------------------------------- |
| **Parallel GC** | Java 8 default                           | High (stop-the-world)                      | Very high      | Moderate                   | Best for **batch jobs** where throughput matters more than latency | Long pause times; bad for low-latency apps               |
| **G1 GC**       | Default since Java 9, incl. 17 & 21      | Low-to-medium                              | High           | Moderate                   | Balances **pause time & throughput**, region-based                 | More tuning needed for very large heaps                  |
| **ZGC**         | Available since Java 11; stable in 15+   | Ultra-low (<10ms, regardless of heap size) | High           | Higher (metadata overhead) | Designed for **low-latency apps**, supports **multi-TB heaps**     | Slightly higher CPU usage; newer compared to G1          |
| **Shenandoah**  | Production-ready in 12+, improved in 17+ | Very low (<10ms)                           | High           | Higher                     | **Concurrent compaction**, low-pause for large heaps               | Not bundled in Oracle JDK (only OpenJDK / RedHat builds) |
| **Serial GC**   | Not default; small heaps                 | High                                       | Low            | Low                        | Simple, good for **small apps / embedded systems**                 | Terrible for large heaps, long pauses                    |

### Latency vs throughput
- In Computing (Networks, Systems, APIs, Databases)
- **Latency** = Time taken to process a single request (or packet).
   - Measured in milliseconds (ms) or seconds.
   - Example: API call response time = 120ms.
- Throughput = Number of requests processed per unit time.
   - Measured in requests per second (RPS), transactions per second (TPS), or Mbps in networking.
   - Example: System can handle 10,000 requests/sec.