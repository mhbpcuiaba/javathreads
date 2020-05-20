package br.com.mhbp.threads.training.executor_service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulleExecutorExample {


    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

        ses.schedule(new Task(), 10, TimeUnit.SECONDS);
        //                                    initialDelay    period
        ses.scheduleAtFixedRate(new Task(),   15,        10, TimeUnit.SECONDS);
                                              //initialDelay    //delay
        ses.scheduleWithFixedDelay(new Task(), 15,        10, TimeUnit.SECONDS);
    }

}


class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("oi");
    }
}