### ✅ Crisp Definition:

The **Proxy Design Pattern** provides a **surrogate or placeholder object** to control access to another object. It acts as an intermediary and can add additional behavior like lazy initialization, access control, logging, etc., without changing the actual object's code.

---

### ⚡ Example in Java:

#### 1️⃣ Subject Interface:

```java
public interface Image {
    void display();
}
```

#### 2️⃣ Real Subject:

```java
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
```

#### 3️⃣ Proxy:

```java
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {  // Lazy loading
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

#### 4️⃣ Client Usage:

```java
public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        image1.display();  // Loads and displays
        image1.display();  // Just displays (doesn't load again)

        image2.display();  // Loads and displays
    }
}
```

---

### ✅ Key Use-Cases:

* **Lazy Initialization**
* **Access Control / Authorization**
* **Logging & Monitoring**
* **Remote Proxy (e.g., in distributed systems)**

---