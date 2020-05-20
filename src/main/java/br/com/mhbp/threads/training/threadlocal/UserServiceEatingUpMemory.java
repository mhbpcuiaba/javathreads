package br.com.mhbp.threads.training.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class UserServiceEatingUpMemory {


    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            String birthDate = new UserServiceEatingUpMemory().brithDate(100234463);
            System.out.println(birthDate);
        }).start();

        Runnable task = () -> {
            String birthDate = new UserServiceEatingUpMemory().brithDate(100234463);
            System.out.println(birthDate);
        };
        
        new Thread(task).start();
        System.out.println("Its OK up to here");
        Thread.sleep(1000);

        for (int i = 0; i < 1_000_000; i++) {
            Executors.newFixedThreadPool(10).submit(task);
        }

        System.out.println("End...?");
    }


    public String brithDate(int dateInMilliseconds) {
        Date date = new Date(dateInMilliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
