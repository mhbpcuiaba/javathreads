package br.com.mhbp.threads.interrupt;

public class InterruptExample {

    public void example() throws InterruptedException {

        Thread sleepyThread = new Thread(() -> {

             try {
                 System.out.println("I am too sleepy... Let me sleep for an hour....");
                 Thread.sleep(1_000 * 60 * 60);
             } catch (InterruptedException e) {
                 System.out.println("The interrupt flag is cleard : " + Thread.interrupted() + " " + Thread.currentThread().isInterrupted());
                 Thread.currentThread().interrupt();
                 System.out.println("Oh someone woke me up ! ");
                 System.out.println("The interrupt flag is set now : " + Thread.currentThread().isInterrupted() + " " + Thread.interrupted());
             }
         });

        sleepyThread.start();
        System.out.println("About to wake up the sleepy thread ...");
        sleepyThread.interrupt();
        System.out.println("Woke up sleepy thread ...");

        sleepyThread.join();
        System.out.println("Game Over");
    }
}
