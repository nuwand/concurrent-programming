package com.nuwan.concurrency.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BankingApp {

    static Map<String, Account> accounts = new HashMap<String, Account>();

    public static void main(String[] args) {

        Account newAccount = new Account("12345", 100);
        accounts.put(newAccount.accountId, newAccount);

        String accountId = "";
        int withdrawalAmount;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (!accountId.equals("exit")) {
                System.out.println("Enter account id : ");
                accountId = bufferedReader.readLine();

                Account account = accounts.get(accountId);
                if (account == null) {
                    throw new Exception("No account with ID found");
                }
                System.out.println("Your account balance is : " + account.getBalance());
                System.out.println("Enter withdrawal amount : ");
                withdrawalAmount = Integer.parseInt(bufferedReader.readLine());

                if(!account.canWithdraw(withdrawalAmount)) {
                    throw new Exception("Insufficient funds!");
                }

                account.setBalance(account.getBalance() - withdrawalAmount);
                System.out.println("New account balance is : " + account.getBalance());
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

}
