package com.prep.interview.multiThreading;

public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(DeadLock::deadlock1, "t1");
        Thread t2 = new Thread(DeadLock::deadlock2, "t2");

        t1.start();
        t2.start();
    }

    private static void deadlock1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + ": Holding lock1...");
            System.out.println(Thread.currentThread().getName() + ": Waiting for lock2...");
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + ": Acquired lock2!");
            }
        }
    }

    private static void deadlock2() {
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + ": Holding lock2...");
            System.out.println(Thread.currentThread().getName() + ": Waiting for lock1...");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": Acquired lock1!");
            }
        }
    }
}
