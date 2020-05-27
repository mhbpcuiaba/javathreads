package br.com.mhbp.threads.semaphore;

public class CountingSemaphore {

    int usedPermits;
    int maxCount;

    public CountingSemaphore(int count) {
        this.maxCount = count;
    }

    public CountingSemaphore(int count, int initialPermits) {
        this.maxCount = count;
        this.usedPermits = this.maxCount - initialPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxCount) wait();

        usedPermits++;
        notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) wait();

        usedPermits--;
        notifyAll();
    }


    public static void main(String[] args) throws InterruptedException {

        CountingSemaphore cs = new CountingSemaphore(1);
        Thread t1 = new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    cs.acquire();
                    System.out.println("Ping: " + i);

                }
            }catch (Exception e) {

            }
        });


        Thread t2 = new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    cs.release();
                    System.out.println("Ping: " + i);

                }
            }catch (Exception e) {

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("GAME OVER");
    }
}
