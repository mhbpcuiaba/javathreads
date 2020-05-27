package br.com.mhbp.threads;

import br.com.mhbp.threads.semaphore.Semaphore;

public class FixedMissedSignalExample {


    public void example() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread signaller = new Thread(() -> {
           semaphore.release();
            System.out.println("Sent signal");
        });

        Thread waiter = new Thread( () -> {
            try {
                semaphore.acquire();
                System.out.println("Received signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        });


        signaller.start();
        signaller.join();

        Thread.sleep(5_000);

        waiter.start();
        waiter.join();
    }
}
