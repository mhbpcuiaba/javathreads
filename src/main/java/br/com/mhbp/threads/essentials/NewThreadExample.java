package br.com.mhbp.threads.essentials;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class NewThreadExample {

    public static void main(String[] args) {
        Thread timeThread = new Thread(() -> {

            while (true) {

                System.out.println(LocalTime.now());

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "HelloThread");

        timeThread.start();

        Thread helloThread = new Thread(() -> {

            while (true) {

                System.out.println("Hello world");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "HelloThread");

        helloThread.start();

        System.out.println("GAME OVER");
    }
}
