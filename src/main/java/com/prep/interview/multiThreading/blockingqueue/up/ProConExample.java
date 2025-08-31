package com.prep.interview.multiThreading.blockingqueue.up;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.format.annotation.DurationFormat;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProConExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2); //default queue size
        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                    Thread.sleep(1000);
                    System.out.println("Producer:" + i);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                int val = 0;
                try {
                    val = queue.take();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Consumer:" + val);
            }
        };
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
