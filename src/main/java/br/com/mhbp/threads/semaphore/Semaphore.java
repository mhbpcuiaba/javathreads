package br.com.mhbp.threads.semaphore;

public class Semaphore {
    private final int MAX_AVAILABLE;
    private int taken;
    java.util.concurrent.Semaphore s;
    public Semaphore(int max_available) {
        MAX_AVAILABLE = max_available;
    }


    public synchronized void acquire() throws InterruptedException {
        while (taken == MAX_AVAILABLE) wait();
        taken++;
    }

    public synchronized void release() {
        taken--;
        notifyAll();
    }
}
