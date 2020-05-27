package br.com.mhbp.threads.training.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

public class UserServiceStaticWithSyncProblem {

        static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            String birthDate = new UserServiceStaticWithSyncProblem().brithDate(new Random().nextInt());
            System.out.println(birthDate);
        };

        new Thread(task).start();
        System.out.println("Its OK up to here");
        Thread.sleep(1000);

        for (int i = 0; i < 1_000; i++) {
            Executors.newFixedThreadPool(10).submit(task);
        }

        System.out.println("End...?");
    }


    public String brithDate(int dateInMilliseconds) {
        Date date = new Date(dateInMilliseconds);
        return sdf.format(date);
    }
}
