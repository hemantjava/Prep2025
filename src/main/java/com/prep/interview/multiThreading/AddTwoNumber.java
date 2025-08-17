package com.prep.interview.multiThreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddTwoNumber {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 10;
        int b = 20;
        System.out.println(Thread.currentThread().getName());
        final int result = getResult(a, b);
        System.out.println(result);
        System.out.println(Thread.currentThread().getName());
    }

    private static int getResult(int a, int b) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Integer> cal1 = ()->{
            Thread.currentThread().setName("cal1");
            System.out.println(a+":"+Thread.currentThread().getName());
            return a;
        };
        Callable<Integer> cal2 = ()->{
            Thread.currentThread().setName("cal2");
            System.out.println(b+":"+Thread.currentThread().getName());
            return b;
        };

        int rs = service.submit(cal1).get() + service.submit(cal2).get();
        service.shutdown();
        return rs;
    }

}
