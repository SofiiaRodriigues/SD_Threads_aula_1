package pt.ipb.dsys.threads.deadlock.transfer;

public class DeadlockTransferMain {

    public static void main(String[] args) {
        Account myAccount = new Account(1000, 10);
        Account yourAccount = new Account(2000, 20);
        new Thread(() -> Account.transferSolved(myAccount, yourAccount, 5)).start();
        new Thread(() -> Account.transferSolved(yourAccount, myAccount, 3)).start();
    }

}
