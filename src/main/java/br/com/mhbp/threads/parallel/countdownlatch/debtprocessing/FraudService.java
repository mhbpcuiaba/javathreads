package br.com.mhbp.threads.parallel.countdownlatch.debtprocessing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FraudService implements Runnable {
    private final CountDownLatch latch;

    public FraudService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Initializing fraud service ...\n"+ Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("fraud service ready !!!\n");
            latch.countDown();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        int[] array = { 1,2,3,4,5,6,7};


        int n = array.length;
        for (int i = 0; i < n/2;i++) {
            int tmp = array[i];
            array[i] = array[n - i -1];
            array[n - i -1] = tmp;

        }

        for (int i : array) {
            System.out.println(i);
        }
    }
}
