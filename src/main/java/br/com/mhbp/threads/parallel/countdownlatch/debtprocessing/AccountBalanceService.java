package br.com.mhbp.threads.parallel.countdownlatch.debtprocessing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AccountBalanceService implements Runnable{
    private final CountDownLatch latch;

    public AccountBalanceService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Initializing account balance service ...\n"+ Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("Account balance service ready !!!\n");
            latch.countDown();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
