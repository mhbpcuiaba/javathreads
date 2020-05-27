package br.com.mhbp.threads.training.conditions;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueueWithWaitAndNotify<E> {

    private Queue<E> queue;
    private int max = 16;
    Object isEmpty = new Object();
    Object isFull = new Object();

    public MyBlockingQueueWithWaitAndNotify(int size) {
        queue = new LinkedList<>();
        max = size;
    }


    public synchronized void put(E e) throws InterruptedException {
            while (queue.size() == max) {
                isFull.wait();
                //wait
            }
            queue.add(e);
            isEmpty.notifyAll();
    }

    public synchronized E take() throws InterruptedException {
            while (queue.isEmpty()) {
                isEmpty.wait();
            }
            E removed = queue.remove();
            isFull.notifyAll();
            return removed;
    }

}
