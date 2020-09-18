package br.com.mhbp.threads.task.execution;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TimerShootout {

    public static void main(String[] args) throws InterruptedException {

        Timer t = new Timer(true);

        t.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("hey, I am a Timer");
            }
        }, 10, 1000);


        Thread.sleep(10000);
        System.out.println("end of main thread");

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3, new ThreadFactory() {


            private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

            @Override
            public Thread newThread(Runnable runnable) {

                Thread thread = defaultFactory.newThread(runnable);
                thread.setDaemon(true);
                return thread;
            }
        });

        ses.scheduleWithFixedDelay(() -> {
            System.out.println("timer 2");
        }, 10, 20, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        System.out.println("end of main thread");

    }
}
