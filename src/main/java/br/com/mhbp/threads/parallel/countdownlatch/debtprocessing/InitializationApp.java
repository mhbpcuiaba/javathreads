package br.com.mhbp.threads.parallel.countdownlatch.debtprocessing;

import java.util.concurrent.CountDownLatch;

public class InitializationApp {
/*
https://www.javaspecialists.eu/archive/Issue257.html
https://dzone.com/articles/java-phasers-made-simple
 */
    public static void main(String[] args) {
        CountDownLatch latchAuth = new CountDownLatch(3);
        CountDownLatch latchFraud = new CountDownLatch(2);
        CountDownLatch latchAccount = new CountDownLatch(4);

        Thread as1 = new Thread(new AuthenticationService(latchAuth));
        as1.setName("AuthenticationServiceThread 1");
        as1.start();
        Thread as2 = new Thread(new AuthenticationService(latchAuth));
        as2.setName("AuthenticationServiceThread 2");
        as2.start();
        Thread as3 = new Thread(new AuthenticationService(latchAuth));
        as3.setName("AuthenticationServiceThread 3");
        as3.start();

        try{
            latchAuth.await();
            System.out.println("Authenticator started");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread fs1 = new Thread(new FraudService(latchFraud));
        fs1.setName("FraudServiceThread1");
        fs1.start();

        Thread fs2 = new Thread(new FraudService(latchFraud));
        fs2.setName("FraudServiceThread2");
        fs2.start();

        try{
            latchAuth.await();
            System.out.println("fraud started");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread bs1 = new Thread(new AccountBalanceService(latchAccount));
        bs1.setName("AccountBalanceServiceThread1");

        bs1.start();
        Thread bs2 = new Thread(new AccountBalanceService(latchAccount));
        bs2.setName("AccountBalanceServiceThread2");
        bs2.start();
        Thread bs3 = new Thread(new AccountBalanceService(latchAccount));
        bs3.setName("AccountBalanceServiceThread3");
        bs3.start();
        Thread bs4 = new Thread(new AccountBalanceService(latchAccount));
        bs4.setName("AccountBalanceServiceThread4");
        bs4.start();
        try{
            latchAccount.await();
            System.out.println("account started");
            System.out.println("initialization successfully accomplished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
