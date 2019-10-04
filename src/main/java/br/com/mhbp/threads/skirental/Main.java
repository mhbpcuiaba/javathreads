package br.com.mhbp.threads.skirental;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        final SkisForRent sfr = new SkisForRent();


        Runnable customer = () -> {
            String name = Thread.currentThread().getName();

            try {
                while (true) {
                    SkiPair sp = sfr.rent();
                    System.out.printf("%s renting %s%n", name, sp.name);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
                    System.out.printf("%s returning %s%n", name, sp.name);
                    sfr.returning(sp);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        };


        ExecutorService[] executors = new ExecutorService[SkisForRent.maxSkiPairs + 1];

        for (ExecutorService executor : executors) {
            executor = Executors.newSingleThreadExecutor(new CustomerFactory());
            executor.execute(customer);
        }
    }
}


class CustomerFactory implements ThreadFactory {

    public  static AtomicLong id = new AtomicLong();
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("Customer " + id.addAndGet(1));
        return thread;
    }
}