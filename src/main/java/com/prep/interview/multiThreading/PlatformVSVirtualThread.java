package com.prep.interview.multiThreading;

public class PlatformVSVirtualThread {
    public static void main(String[] args) {
        // Platform Thread (Traditional)
        Thread platformThread = new Thread(() -> System.out.println("Running on a platform thread: "
                + Thread.currentThread()));
        platformThread.start();

     // Virtual Thread (Java 19+)
        Thread virtualThread = Thread.ofVirtual().start(() -> System.out.println("Running on a virtual thread: "
                + Thread.currentThread()));
        System.out.println(virtualThread.isVirtual());
    }
}
