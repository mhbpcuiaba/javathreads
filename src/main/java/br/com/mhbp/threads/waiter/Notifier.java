package br.com.mhbp.threads.waiter;

public class Notifier implements Runnable {

    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");

        try {
            Thread.sleep(1_000);

            synchronized (msg) {
                msg.setMsg(name + " notified work done");
                msg.notifyAll();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
