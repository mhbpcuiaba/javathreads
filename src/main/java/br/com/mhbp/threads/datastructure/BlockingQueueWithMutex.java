package br.com.mhbp.threads.datastructure;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithMutex<T> {
    T[] array;
    Lock lock = new ReentrantLock();
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;


    public BlockingQueueWithMutex(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }


    public T dequeue() {

        lock.lock();

        while (size == 0) {
            lock.unlock();
            lock.lock();
        }

        if (head == capacity) head = 0;

        T element = array[head];
        array[head] = null;
        head++;
        size--;

        lock.unlock(); //should be into finally block
        return element;
    }

    public  void enqueue(T element) {

        lock.lock();

        while (size == capacity) {
            lock.unlock();
            lock.lock();
        }

        if (tail == capacity) tail = 0;

        array[tail] = element;
        tail ++;
        size++;
        lock.unlock();
    }
}
