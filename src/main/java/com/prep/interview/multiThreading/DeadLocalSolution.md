Deadlocks are one of the trickiest concurrency issues in Java. They happen when two or more threads wait for each other’s resources, causing the program to freeze forever.

Here’s a complete guide 👇 on how to avoid deadlocks:

⸻

✅ 1. Use a Consistent Lock Ordering (Most Common Solution)

Always acquire multiple locks in the same order across all threads.

Example (Fixed version of previous deadlock):

public class Main {
private static final Object lock1 = new Object();
private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> task1());
        Thread t2 = new Thread(() -> task2());

        t1.start();
        t2.start();
    }

    private static void task1() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock1...");
            synchronized (lock2) {
                System.out.println("Thread 1: Holding lock1 & lock2.");
            }
        }
    }

    private static void task2() {
        // Follow the same order: lock1 → lock2
        synchronized (lock1) {
            System.out.println("Thread 2: Holding lock1...");
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock1 & lock2.");
            }
        }
    }
}

✅ Why it works:
Both threads try to acquire lock1 first, so they never wait in a circular way.

⸻

✅ 2. Use tryLock() with Timeout (From java.util.concurrent.locks)

Instead of blocking indefinitely, a thread tries to acquire a lock and backs off if not available.

Example:

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Main {
private static final Lock lock1 = new ReentrantLock();
private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(Main::task1).start();
        new Thread(Main::task2).start();
    }

    private static void task1() {
        try {
            if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Thread 1: Got lock1");
                    if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Thread 1: Got lock2");
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println("Thread 1: Could not get lock2, backing off.");
                    }
                } finally {
                    lock1.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void task2() {
        try {
            if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Thread 2: Got lock2");
                    if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Thread 2: Got lock1");
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println("Thread 2: Could not get lock1, backing off.");
                    }
                } finally {
                    lock2.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

✅ Why it works:
Threads don’t wait forever. If they can’t acquire the second lock, they release the first one and retry later — avoiding deadlock.

⸻

✅ 3. Minimize Lock Scope

Keep the synchronized code as small as possible so locks are released quickly.

// ❌ Bad: lock held for too long
synchronized(lock) {
heavyComputation();
dbWrite();
}

// ✅ Better: lock only critical section
synchronized(lock) {
sharedResource++;
}
heavyComputation();


⸻

✅ 4. Use Higher-Level Concurrency Utilities

Use tools from java.util.concurrent like:
•	ConcurrentHashMap (instead of synchronizing maps)
•	BlockingQueue (instead of manual locks)
•	Semaphore, ReadWriteLock, etc.

These reduce the chance of writing deadlock-prone code.

⸻

✅ 5. Avoid Nested Locks (If Possible)

Deadlocks often occur when threads try to acquire multiple locks at once. If you can redesign the code to require only one lock at a time — do it.

⸻

🧠 Summary Table

Strategy	Explanation	Best For
Lock ordering	Always acquire locks in the same order	Most common and simplest
tryLock()	Attempt lock with timeout, back off if needed	High-performance systems
Minimize lock scope	Hold locks for shortest time possible	General good practice
Use concurrency utilities	Use higher-level abstractions	Production-ready code
Avoid nested locks	Simplify locking logic	Complex systems


⸻

💡 Pro tip: Always test concurrent code with tools like jconsole, VisualVM, or jstack — they can show deadlock threads and help you debug.