package br.com.mhbp.threads.cyclic.barrier;

import java.util.Collections;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task implements Runnable {


    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, () -> {
            System.out.println("all parties finished");
        });


        Thread t1 = new Thread(new Task(cb));
        Thread t2 = new Thread(new Task(cb));
        Thread t3 = new Thread(new Task(cb));

        t1.start();
        t2.start();
        t3.start();

    }
    final CyclicBarrier ccb;

    public Task(CyclicBarrier ccb) {
        this.ccb = ccb;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
            ccb.await();
            System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
