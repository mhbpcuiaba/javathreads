package br.com.mhbp.threads.training.conditions;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *     Blocking threads
 *
 *     Object.wait
 *     Thread.sleep
 *     Thread.join
 *
 *
 *     all of them have hold a thread on running state
 */


public class SharedFifoQueue {

    private Object[] elements;
    private int current = 0;
    private int placeIndex = 0;
    private int removeIndex = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();

    public SharedFifoQueue(int capacity) {
        elements = new Object[capacity];
    }



    public void test() throws InterruptedException {
        elements.wait();
    }

    public void teste2() throws InterruptedException {
        Thread.sleep(1_000);
    }
    public void add(Objects e) throws InterruptedException {

        try {
            lock.lock();

            while (current == elements.length) isFull.await();

            elements[placeIndex] = e;

            placeIndex = (placeIndex + 1) % elements.length;
            current++;

            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object remove() throws InterruptedException {

        while (current < 0) isEmpty.await();

        try {
            lock.lock();

            Object e = elements[removeIndex];

            removeIndex = (removeIndex + 1) % elements.length;
            --current;
            isFull.signalAll();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
