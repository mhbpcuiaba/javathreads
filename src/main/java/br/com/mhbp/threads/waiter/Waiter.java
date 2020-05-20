package br.com.mhbp.threads.waiter;

public class Waiter implements Runnable {

    private Message msg;

    public Waiter(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        synchronized (msg) {
            try {
                System.out.println(name = " is waiting to get notified at time: " + System.currentTimeMillis());
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " waiter thread got notified at time: " + System.currentTimeMillis());
            System.out.println(name + " processed " + msg.getMsg());
        }
    }
}
