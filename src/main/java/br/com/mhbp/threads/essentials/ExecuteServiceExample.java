package br.com.mhbp.threads.essentials;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteServiceExample {

    public static void main(String[] args) {

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        ses.scheduleAtFixedRate(() -> System.out.println(LocalTime.now()), 1, 1, TimeUnit.SECONDS);

        ses.scheduleAtFixedRate(() -> System.out.println("Hello World"), 1, 200, TimeUnit.MILLISECONDS);

        System.out.println("GAME OVER");
    }
}
