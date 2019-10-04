package br.com.mhbp.threads.spendthrift;

class Account {
    public static int balance;
//    public static final Object lock = new Object();
}

class DepositTransaction extends Thread {
    private int howmuch;

    DepositTransaction(int howmuch) {
        this.howmuch = howmuch;
    }

    @Override
    public void run() {
        for (int i = 0; i < howmuch; i++) {
//            synchronized (Account.lock) {
                Account.balance++;
//            }
        }
    }
}

class WithdrawTransaction extends Thread {
    private int howmuch;

    WithdrawTransaction(int howmuch) {
        this.howmuch = howmuch;
    }

    @Override
    public void run() {
        for (int i = 0; i < howmuch; i++) {
//            synchronized (Account.lock) {
                Account.balance--;
//            }
        }
    }
}

public class RaceCondition {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("RunCondition <times to iterate>");
            return;
        }

        int howmuch = Integer.parseInt(args[0]);
        DepositTransaction depositTransaction = new DepositTransaction(howmuch);
        WithdrawTransaction withdrawTransaction = new WithdrawTransaction(howmuch);

        depositTransaction.start();
        withdrawTransaction.start();

        try {
            depositTransaction.join();
            withdrawTransaction.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("Final balance: " + Account.balance);


    }
}