package com.prep.interview.multi_threading.synchronization;

public class GpayTransaction extends Thread {

    final Account account;
    final double aDouble;

    public GpayTransaction(String name, Account account, final double aDouble) {
        super(name);
        this.account = account;
        this.aDouble = aDouble;
    }

    @Override
    public void run() {
        account.deposit(aDouble);
    }
}