package br.com.mhbp.threads.parallel.countdownlatch.debtprocessing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AuthenticationService implements Runnable {
    private final CountDownLatch latch;

    public AuthenticationService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Initializing authentication service ...\n"+ Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("Authentication service ready !!!\n");
            latch.countDown();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
