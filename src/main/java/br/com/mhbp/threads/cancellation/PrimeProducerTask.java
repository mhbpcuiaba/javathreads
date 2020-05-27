package br.com.mhbp.threads.cancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducerTask implements Runnable {

    private final BlockingQueue<BigInteger> queue;

    public PrimeProducerTask(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger bi = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) queue.put(bi.nextProbablePrime());
        } catch (InterruptedException consumed) {
            consumed.printStackTrace();
            /* Allow thread to exit */
        }
    }
}
