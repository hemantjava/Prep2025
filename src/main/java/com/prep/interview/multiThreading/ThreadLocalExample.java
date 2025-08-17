package com.prep.interview.multiThreading;

public class ThreadLocalExample {

    // Create a ThreadLocal variable
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 3; i++) {
                threadLocal.set(threadLocal.get() + 1);
                System.out.println(Thread.currentThread().getName() +
                        " -> " + threadLocal.get());
            }
            threadLocal.remove(); // It mandatory to clean thread local variable or else lead to memory leakage issues
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
        /* output
Thread-1 -> 1
Thread-1 -> 2
Thread-1 -> 3
Thread-2 -> 1
Thread-2 -> 2
Thread-2 -> 3

         */
    }
}
