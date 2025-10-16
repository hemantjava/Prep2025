---

## 🔥 What Is Garbage Collection?

* **Garbage Collection (GC)** *
 is the automatic process of finding and reclaiming memory that is no longer reachable by any 
  part of your program — so you don’t have to manually free memory.

---

## 🧠 JVM Memory Model (Simplified)

* **Young Generation**:

    * **Eden**: where new objects are created.
    * **Survivor S0/S1**: objects that survive collections are copied here.

* **Old Generation (Tenured)**:

    * Long-lived objects eventually move here.

---

## 🔄 Garbage Collection Internal Flow

Here’s how GC happens **step-by-step** 👇

---

### 🌱 1. Allocation Phase (Eden Space)

* All new objects are allocated in **Eden**.
* When Eden fills up → **Minor GC** is triggered.

---

### 🧹 2. Minor GC (Young Generation Collection)

Minor GC is **fast and frequent**.

**Steps:**

1. **Stop-the-world (STW)**: All threads pause briefly.
2. **Root Scanning**: GC scans **GC Roots** (stack variables, static fields, JNI refs) to find reachable objects.
3. **Mark Phase**: All reachable objects in Eden and Survivor spaces are marked.
4. **Copy Phase**:

    * Reachable objects from **Eden → Survivor (S0)**.
    * From **Survivor (S0 → S1)** if they survive multiple GCs.
    * Objects exceeding the **tenuring threshold** → moved to **Old Gen**.
5. **Clean Phase**: Unreachable objects are discarded (memory reclaimed).

📊 **Result:** Eden and one Survivor space are empty, and surviving objects are compacted.

---

### 🏗️ 3. Promotion to Old Generation

* Objects that **survive multiple Minor GCs** are promoted to the **Old Generation**.
* Old Gen holds long-lived objects (e.g., caches, sessions, metadata).

---

### 🧓 4. Major GC / Full GC (Old Generation Collection)

When Old Gen is full, **Major GC** or **Full GC** runs.
This is **slower and more expensive** (can pause for hundreds of ms).

**Steps:**

1. **Stop-the-world (STW):** All threads pause.
2. **Mark Phase:** GC starts from GC Roots and **traces reachable objects** across heap.
3. **Sweep Phase:** Unreachable objects are freed.
4. **Compact Phase (optional):** Remaining objects are **compacted** to reduce fragmentation.

✅ After Full GC, most of the heap is reclaimed.

---

### 🔄 GC Lifecycle Diagram (Simplified)

Here’s a diagram showing the full lifecycle of objects through GC:

```
        ┌────────────────────────────┐
        │        New Object         │
        │       Allocation          │
        └────────────┬──────────────┘
                     │
                     ▼
          ┌────────────────────┐
          │   Eden (Young)     │
          └────────────────────┘
                     │
             Minor GC Triggered
                     │
                     ▼
     ┌────────────────────────────────┐
     │  Survivors (S0/S1)             │
     │  - Objects copied if alive     │
     │  - Objects counted for age     │
     └────────────────────────────────┘
                     │
     ┌───────────────┴────────────────┐
     │  Survive > N Minor GCs?        │
     └───────────────┬────────────────┘
                     │ Yes
                     ▼
          ┌────────────────────┐
          │  Old Generation    │
          │ (Long-lived objs)  │
          └────────────────────┘
                     │
             Major GC Triggered
                     │
                     ▼
          ┌────────────────────┐
          │   Mark & Sweep     │
          │   Compact Memory   │
          └────────────────────┘
```

---

## 🧠 GC Root Sources (Important for Mark Phase)

GC tracing starts from **GC Roots** (objects always considered reachable):

* Local variables in stack frames
* Static fields of loaded classes
* Active JNI references
* JVM system classes
* Active threads

---

## 📈 Optimization Tips

| Optimization                              | Why it Helps                                     |
| ----------------------------------------- | ------------------------------------------------ |
| **Minimize object churn**                 | Reduces Minor GC frequency                       |
| **Use primitive collections**             | Less object overhead                             |
| **Avoid long-lived references**           | Prevent memory leaks                             |
| **Use `-Xmx` and `-Xms` wisely**          | Too small = frequent GC, Too large = long pauses |
| **Choose right GC (G1, ZGC, Shenandoah)** | Depends on latency vs. throughput needs          |
| **Use `-XX:+PrintGCDetails` or JFR**      | Analyze GC behavior and tune                     |

---

## 🔬 Advanced: G1 GC Phases (Internals)

G1 GC divides the heap into regions and performs GC **concurrently**:

1. Initial Mark (STW)
2. Root Region Scan (Concurrent)
3. Concurrent Mark
4. Remark (STW)
5. Cleanup & Evacuation (Mostly Concurrent)

This reduces pause times significantly.

---

## ✅ Summary

| Phase         | Description                            | Trigger                       |
| ------------- | -------------------------------------- | ----------------------------- |
| **Minor GC**  | Cleans up **Young Generation**         | Eden fills up                 |
| **Promotion** | Moves surviving objects to **Old Gen** | After several Minor GCs       |
| **Major GC**  | Cleans **Old Generation**              | Old Gen is full               |
| **Full GC**   | Cleans entire heap (Young + Old)       | Rare, usually memory pressure |

---

### 💡 Why It Matters:

* Understanding GC flow helps you **design memory-efficient apps**, **tune performance**, and **avoid OOM errors**.
* In interviews and production debugging, knowing how objects move and when GC triggers gives you a huge advantage.

---
