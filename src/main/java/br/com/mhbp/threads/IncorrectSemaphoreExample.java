package br.com.mhbp.threads;

import br.com.mhbp.threads.semaphore.Semaphore;

public class IncorrectSemaphoreExample {

    public void example() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread( () -> {

            while (true) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //if you do not write the finally below to release a semaphore, you will be blocked forever
//                finally {
//                    semaphore.release();
//                }

                throw new RuntimeException("Suppose somthing went deadly wrong");
            }
        });

        badThread.start();

        Thread.sleep(1_000);

        Thread goodThread = new Thread( () -> {
            System.out.println("good thread wainting to signalled");

            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        goodThread.start();
        goodThread.join();
    }
}
