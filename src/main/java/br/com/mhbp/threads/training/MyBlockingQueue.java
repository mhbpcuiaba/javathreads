package br.com.mhbp.threads.training;

public class MyBlockingQueue<T> {

    T[] array;
    int size;
    int capacity;
    int head;
    int tail;


    public MyBlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {

        while (size == capacity)  wait();

        //reset tail to the beginning if the tail is already at the end of the backing array
        if (tail == capacity) {
            tail = 0;
        }
        array[size++] = item;
        tail++;//???

        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {

        while (size == 0) wait();


        if (head == capacity) head = 0;

        T item = array[head];
        array[head] = null;
        head++;
        size--;
        notifyAll();
        return item;
    }
}
