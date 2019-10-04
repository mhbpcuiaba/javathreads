package br.com.mhbp.threads.spendthrift;

import java.util.Random;

public class StackTS {

    private static final int capacity = 8;
    private int top = -1;
    private int[] stack = new int[capacity];

    public synchronized void push(Integer n) {

        while ((top + 1) == capacity) {
            try {
                wait();
            } catch (InterruptedException e) { }
            log(n + "pushed at " + (++top));
            stack[top] = n;
            notifyAll();
        }
    }

    public synchronized void pop() {
        while(top < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log(stack[top] + "popped at " + top);
        top--;
        notifyAll();
    }


    private void log(String s) {
        System.out.println(s);
    }
}

class Pusher extends Thread {
    private Random rand = new Random();
    private StackTS stack;

    Pusher(StackTS stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true) {

            stack.push(rand.nextInt());
            //simulating some processing
            try {
                Thread.sleep(rand.nextInt());
            } catch (InterruptedException e) {}
        }
    }
}

class Popper extends Thread {
    private Random rand = new Random();
    private StackTS stack;

    Popper(StackTS stack) {
        stack.pop();
    }

    @Override
    public void run() {
        while (true) {

            stack.pop();
            //simulating some processing
            try {
                Thread.sleep(rand.nextInt());
            } catch (InterruptedException e) {}
        }
    }
}

class MainTS {
    public static void main(String[] args) {
        StackTS stack = new StackTS();
        new Popper(stack).start();
        new Pusher(stack).start();
        new Pusher(stack).start();
    }
}