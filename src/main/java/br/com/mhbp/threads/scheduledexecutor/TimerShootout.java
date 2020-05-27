package br.com.mhbp.threads.scheduledexecutor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TimerShootout {

    /*
        newFixedThreadPool
        newSingleThreadExecutor
        newCachedThreadPool
        newScheduledThreadPool
        newSingleThreadScheduledExecutor

     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hey, I am a timer");
            }
        };

        timer.schedule(timerTask, 100);

        ScheduledExecutorService timer2 = Executors.newScheduledThreadPool(3, new ThreadFactory() {

            private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = defaultFactory.newThread(r);
                thread.setDaemon(true);
                return thread;
            }

        });


        timer2.schedule(() ->{
            System.out.println("hey you!! I am timer 2");
        }, 3, TimeUnit.SECONDS);
    }
}
