package br.com.mhbp.threads.training.conditions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

    private Queue<E> queue;
    private int max = 16;
    Lock l = new ReentrantLock(true);
    Condition isEmpty = l.newCondition();
    Condition isFull = l.newCondition();
//    l.newC

    public MyBlockingQueue(int size) {
        queue = new LinkedList<>();
        max = size;
    }


    public void put(E e) throws InterruptedException {

        l.lock();
        try {

            while (queue.size() == max) {
                isFull.await();
                //wait
            }

            queue.add(e);

            isEmpty.signalAll();
        } finally {
            l.unlock();
        }
    }

    public E take() throws InterruptedException {
        l.lock();
        try {

            while (queue.isEmpty()) {
                isEmpty.await();
            }
            E removed = queue.remove();
            isFull.signalAll();
            return removed;
        }finally {
            l.unlock();
        }

    }
}
