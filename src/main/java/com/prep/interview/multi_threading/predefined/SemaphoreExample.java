package com.prep.interview.multi_threading.predefined;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SemaphoreExample {

    // Semaphore with 3 permits (like 3 parking slots)
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(new Worker("Car-" + i)).start();
        }
    }

    record Worker(String name) implements Runnable {


        @Override
        public void run() {
            try {
                System.out.println(name + " is waiting for a parking slot...");

                // Acquire permit (wait if no permits available)
                semaphore.acquire();
                System.out.println(name + " got a parking slot!");

                // Simulate time in parking
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 6) * 2000L);

                System.out.println(name + " is leaving the parking slot...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release permit so others can enter
                semaphore.release();
                System.out.println(name + " released the slot. Available slots: " + semaphore.availablePermits());
            }
        }
    }
}
