package br.com.mhbp.threads.training.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

import static br.com.mhbp.threads.training.threadlocal.UserServiceStaticWithSyncProblem.sdf;

public class UserServiceWithThreadLocal {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            String birthDate = new UserServiceStaticWithSyncProblem().brithDate(new Random().nextInt());
            System.out.println(birthDate);
        };

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

class SimpleDatFormattThreadLocal {


    public static ThreadLocal<SimpleDateFormat> dateFormatter8 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };
}