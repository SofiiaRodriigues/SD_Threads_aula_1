package pt.ipb.dsys.threads.deadlock.account;

import pt.ipb.dsys.threads.util.SD;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Account {

    private final int number;

    private double balance;

    public Account(int number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    void withdraw(double amount) {
        balance -= amount;
    }

    void deposit(double amount) {
        balance += amount;
    }

    static void transferSolved(Account from, Account to, double amount) {
        SD.sleep(10);

        List<Account> accountList = Arrays.asList(from, to);
        accountList.sort(Comparator.comparing(Account::getNumber));

        internalTransfer(accountList.get(0), accountList.get(1), amount);
    }

    static void transfer(Account from, Account to, double amount) {
        SD.sleep(10);
        internalTransfer(from, to, amount);
    }

    private static void internalTransfer(Account from, Account to, double amount) {
        synchronized (from) {
            System.out.printf("Acquired lock for account %d, waiting for lock for %d\n", from.getNumber(), to.getNumber());
            synchronized (to) {
                System.out.printf("Acquired lock for account %d\n", to.getNumber());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.printf("Transfered %.2f from %d to %d\n", amount, from.getNumber(), to.getNumber());
            }
        }
    }
}