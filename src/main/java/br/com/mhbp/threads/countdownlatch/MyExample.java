package br.com.mhbp.threads.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class MyExample {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> {
            try {
                System.out.println("inicio 1");
                Thread.sleep(1000);
                System.out.println("count 1");
                countDownLatch.countDown();
                System.out.println("fim 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println("inicio 2");
                Thread.sleep(200);
                System.out.println("count 2");
                countDownLatch.countDown();
                System.out.println("fim 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println("inicio 3");
                Thread.sleep(300);
                System.out.println("count 3");
                countDownLatch.countDown();
                System.out.println("fim 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("espera filhos");
        countDownLatch.await();
        System.out.println("fim de tudo");
    }
}
