package br.com.mhbp.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MissedSignalExample {

    public void example() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread signaller = new Thread(() -> {
            lock.lock();
            condition.signal();
            System.out.println("Sent signal");
            lock.unlock();

        });

        Thread waiter = new Thread(() -> {

            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {

            }finally {
                lock.unlock();
            }
        });

        signaller.start();
        signaller.join();

        waiter.start();
        waiter.join();
        System.out.println("Program exiting");
    }
}
