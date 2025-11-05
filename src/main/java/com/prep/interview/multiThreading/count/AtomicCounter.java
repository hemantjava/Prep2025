package com.prep.interview.multiThreading.count;

import java.util.concurrent.atomic.AtomicInteger;

//Increment shared counter with multiple threads
public class AtomicCounter {
private AtomicInteger count = new AtomicInteger(0);

private void increment(){
    count.incrementAndGet();
}
private int getCounter(){
    return count.get();
}
    public static void main(String[] args) throws InterruptedException {
  AtomicCounter atomicCounter = new AtomicCounter();

  Runnable task = ()->{
      for (int i = 0; i < 1000 ; i++) {
          atomicCounter.increment();
      }
  };

  Thread t1 = new Thread(task);
  Thread t2 = new Thread(task);

  t1.start();
  t2.start();
  t1.join();
  t2.join();

        System.out.println("Total count is :"+atomicCounter.getCounter());

    }
}
