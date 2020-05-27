package br.com.mhbp.threads.scheduledexecutor;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExamples {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
//                while (true) {
                System.out.println("the thread is only my, die all");
                throw new RuntimeException("booom");
//                }
            }
        };
        timer.schedule(tt, 500);


        Thread.sleep(400);
        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {

                    System.out.println("hell, i am task 2");

            }
        }, 500);


        Thread.sleep(2000);
        System.out.println("end !!!");
    }
}
