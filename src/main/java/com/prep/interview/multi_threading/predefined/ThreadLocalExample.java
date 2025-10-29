package com.prep.interview.multi_threading.predefined;

class UserContext {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void setUser(String user) {
        currentUser.set(user);
    }

    public static String getUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove(); // Prevent memory leaks
    }
}

// Simulating multiple threads
public class ThreadLocalExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            UserContext.setUser(Thread.currentThread().getName());
            System.out.println("User for " + Thread.currentThread().getName() + ": " + UserContext.getUser());
            UserContext.clear(); // It mandatory to clean thread local variable or else lead to memory leakage issues

        };

        Thread t1 = new Thread(task, "Alice");
        Thread t2 = new Thread(task, "Bob");
        t1.start();
        t2.start();
    }
}

