---

## ☕️ Java 17 – LTS Release (Sept 2021)

Java 17 is a **Long-Term Support (LTS)** version after Java 11 — widely used in production.
It brought several **powerful language enhancements, library improvements, and JVM changes.**

---

## 🔟 Top 10 Features of Java 17

---

### 1. **Sealed Classes & Interfaces (Finalized in Java 17)**

✅ **Purpose:** Control which classes can extend or implement a class/interface.

* Improves **encapsulation**, **security**, and **maintainability**.

```java
sealed class Shape permits Circle, Square {}

final class Circle extends Shape {}
final class Square extends Shape {}
// ❌ class Triangle extends Shape {} // Compile-time error
```

📌 **Use Case:** Limit subclassing to a known set (great for domain modeling, enums-like hierarchies).

---

### 2. **Pattern Matching for `switch` (Preview)**

✅ **Purpose:** Use `switch` with type patterns — safer and more concise.

```java
static String formatShape(Object shape) {
    return switch (shape) {
        case Circle c -> "Circle radius: " + c.radius;
        case Square s -> "Square side: " + s.side;
        default -> "Unknown shape";
    };
}
```

📌 **Benefit:** Removes boilerplate `instanceof` + casting.

---

### 3. **Pattern Matching for `instanceof` (Final)**

✅ **Purpose:** Combine type check and cast in a single step.

```java
if (obj instanceof String s) {
    System.out.println("Length: " + s.length());
}
```

📌 **Before:** `if(obj instanceof String) { String s = (String)obj; ... }`
📌 **Now:** Cleaner and type-safe.

---

### 4. **Text Blocks (Standard)**

✅ **Purpose:** Write multi-line strings without concatenation or escape sequences.
(Previewed in Java 13+, finalized in 15, widely used from 17 onwards.)

```java
String json = """
    {
      "name": "Hemant",
      "role": "Developer"
    }
    """;
```

📌 **Use Case:** JSON, HTML, SQL queries — much more readable.

---

### 5. **Record Classes (Final)**

✅ **Purpose:** Quick way to create immutable data carrier classes.

* Auto-generates `equals()`, `hashCode()`, `toString()`.

```java
public record Employee(String name, double salary) {}

Employee e = new Employee("Hemant", 12000);
System.out.println(e.name());   // Accessor, not getter
```

📌 **Use Case:** DTOs, configuration objects, response models.

---

### 6. **Helpful NullPointerExceptions (JEP 358)**

✅ **Purpose:** Better debugging — shows which variable was null.

```java
String s = null;
System.out.println(s.length());
```

📤 Output (Java 17):

```
Exception: Cannot invoke "String.length()" because "s" is null
```

📌 **Benefit:** Faster debugging and root-cause identification.

---

### 7. **Enhanced Pseudo-Random Number Generators (JEP 356)**

✅ **Purpose:** New algorithms (`L64X128MixRandom`, `Xoshiro256PlusPlus`) and better APIs.

```java
RandomGenerator rnd = RandomGenerator.of("L64X128MixRandom");
System.out.println(rnd.nextInt(100));
```

📌 **Use Case:** Simulations, ML, cryptography, gaming, etc.

---

### 8. **Foreign Function & Memory API (Incubator)**

✅ **Purpose:** Interact with native code (C/C++) without JNI.

* Still incubating, but significant future feature.

```java
// Pseudo-code (preview example)
try (MemorySegment segment = MemorySegment.allocateNative(100)) {
    MemoryAccess.setIntAtOffset(segment, 0, 42);
    int value = MemoryAccess.getIntAtOffset(segment, 0);
}
```

📌 **Benefit:** Faster, safer native interop.

---

### 9. **Strong Encapsulation of JDK Internals (JEP 403)**

✅ **Purpose:** `sun.*` and internal APIs are **no longer accessible** by default.

* Encourages clean public API usage.

```java
// ❌ Accessing internal APIs will cause IllegalAccessError now.
```

📌 **Benefit:** More secure and stable JDK.

---

### 10. **Deprecation & Removal of Legacy Features**

✅ **Purpose:** Clean up old, unsafe, or rarely used features.

* **Applet API** → Removed 🗑️
* **Security Manager** → Deprecated for removal
* **RMI Activation System** → Removed
* **Experimental AOT/JIT compiler** → Removed

📌 **Benefit:** Leaner, faster, more modern JDK.

---

## 🧠 Quick Recap Table

| Feature                             | Description                     | Example                                  |
| ----------------------------------- | ------------------------------- | ---------------------------------------- |
| **Sealed Classes**                  | Restrict inheritance            | `sealed class A permits B {}`            |
| **Pattern Matching for switch**     | Type-safe `switch` with pattern | `switch(obj) { case String s -> ... }`   |
| **Pattern Matching for instanceof** | Auto-cast after type check      | `if (o instanceof String s)`             |
| **Text Blocks**                     | Multi-line strings              | `""" {...} """`                          |
| **Records**                         | Immutable data classes          | `record Emp(String name, int id)`        |
| **Helpful NPEs**                    | Better null error messages      | `"because \"x\" is null"`                |
| **Enhanced PRNG**                   | Better random algorithms        | `RandomGenerator.of("L64X128MixRandom")` |
| **Foreign Memory API**              | Safer native interop            | `MemorySegment.allocateNative()`         |
| **JDK Encapsulation**               | Blocks internal API use         | `IllegalAccessError`                     |
| **Legacy Cleanup**                  | Removed old APIs                | Applets, RMI, etc.                       |

---

✅ **Interview Tip:**
If asked **“Which Java version should you use for production?”** — say **Java 17 LTS** (supported till 2029) and mention **Sealed Classes**, **Records**, and **Pattern Matching** as key reasons.

---

Would you like me to give a **real-world project example combining `sealed`, `record`, and `switch pattern`** together? (This is a strong interview impression 💡)
