package com.prep.interview.multiThreading.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueue {
    public static void main(String[] args) {
        //Create BlockingQueue with capacity 5
        BlockingQueue<Integer> queue =  new LinkedBlockingQueue<>(5);
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        //start the threads
        consumerThread.start();
        producerThread.start();

    }
}
