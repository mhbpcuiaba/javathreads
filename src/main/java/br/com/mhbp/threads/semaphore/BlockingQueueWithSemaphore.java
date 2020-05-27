package br.com.mhbp.threads.semaphore;

public class BlockingQueueWithSemaphore<T> {
    T[] array;
    int size;
    int capacity;
    int head;
    int tail;
    CountingSemaphore semLock = new CountingSemaphore(1, 1);
    CountingSemaphore semProducer;
    CountingSemaphore semConsumer;

    public BlockingQueueWithSemaphore(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        semProducer = new CountingSemaphore(capacity, capacity);
        semConsumer = new CountingSemaphore(capacity, 0);
    }

    public T dequeue() throws InterruptedException {

        T item = null;
        semConsumer.acquire();
        semLock.acquire();

        if (head == capacity) head = 0;
        T result = array[head];
        array[head] = null;
        head++; //why?????????????????????
        size--;

        semLock.release();
        semConsumer.release();

        return  result;
    }

    public void enqueue(T el) throws InterruptedException {


        semProducer.acquire();
        semLock.acquire();

        if (tail == capacity) tail = 0;

        array[tail] = el;

        tail++;// why ??
        size++;

        semLock.release();
        semProducer.release();


    }
}
