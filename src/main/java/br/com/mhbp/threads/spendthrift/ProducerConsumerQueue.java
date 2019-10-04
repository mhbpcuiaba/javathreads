package br.com.mhbp.threads.spendthrift;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerQueue {

    public static void main(String[] args) {
        BlockingQueue<String> dropbox = new SynchronousQueue<String>();
        new Thread(new Producer(dropbox)).start();
        new Thread(new Consumer(dropbox, 12)).start();
        new Thread(new Producer(dropbox)).start();
    }

}

class Consumer implements Runnable {
    private BlockingQueue<String> dropbox;
    private int maxTakes;

    public Consumer(BlockingQueue<String> dropbox, int maxTakes) {
        this.dropbox = dropbox;
        this.maxTakes = maxTakes;
    }

    public void run() {

        Random random = new Random();

        try {
            for (int i = 0; i < this.maxTakes; i++) {
                String message = dropbox.take();
                System.out.println("message received: " + message);
                Thread.sleep(random.nextInt(3000     ));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Producer implements Runnable {

    private BlockingQueue<String> dropbox;

    Producer(BlockingQueue<String> dropbox) {
        this.dropbox = dropbox;
    }

    public void run() {

        String importantInfo[] = {
          "Managed holistic contingency will grow killer action-items.",
          "Vision-oriented zero administration time-frame will generate back-end interfaces",
          "Triple-buffered scalable services will productize visionary infomediarties.",
          "Reactive radical knowldge base will aggreagte extensible vortals.",
          "Face to face client-server pricing structure will whiteboard robust communities.",
          "Future-proofed 5th generation protocols will strategize web-enabled networks."
        };

        try {
            for (String s : importantInfo) {
                dropbox.put(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
