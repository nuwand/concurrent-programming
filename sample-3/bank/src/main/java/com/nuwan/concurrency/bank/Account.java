package com.nuwan.concurrency.bank;

public class Account {

    String accountId;

    int balance;

    public Account(String accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        this.balance = balance - amount;
    }

    public boolean canWithdraw(int amount) {
        return balance - amount > 0;
    }

}
