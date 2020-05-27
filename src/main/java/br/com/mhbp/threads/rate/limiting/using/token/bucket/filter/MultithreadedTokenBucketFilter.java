package br.com.mhbp.threads.rate.limiting.using.token.bucket.filter;

import java.util.HashSet;

public class MultithreadedTokenBucketFilter {


    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> allThreads = new HashSet<>();

        MultithreadedTokenBucketFilter mtbf = MultithreadedTokenBucketFilter.create(1);
        for (int i = 0; i < 1_00; i++) {

            Thread t = new Thread(() -> {
                try {
                    mtbf.getToken();
                } catch (InterruptedException e) {
                    System.out.println("Erro when trying to get token by threaddr: " + Thread.currentThread().getName());
                }
            });
            t.setName("TokenConsumer-" + i );
            allThreads.add(t);
        }

        Thread.sleep(ONE_SECOND);

        for (Thread thread : allThreads) {
            thread.start();
        }

        for (Thread thread : allThreads) {
            thread.join();
        }
    }


    long possibleTokens;
    int maxTokens;
    static final int ONE_SECOND = 1_000;

    public MultithreadedTokenBucketFilter(int tokens) {
        this.maxTokens = tokens;

    }

    private void initialize() {
        Thread t = new Thread(() -> {
            try {
                daemonThread();
            } catch (InterruptedException e) {
                throw new RuntimeException("token producer is dead");
            }
        });

        t.setDaemon(true);
        t.start();
    }

    static MultithreadedTokenBucketFilter create(int capacity) {
        MultithreadedTokenBucketFilter multithreadedTokenBucketFilter = new MultithreadedTokenBucketFilter(capacity);
        multithreadedTokenBucketFilter.initialize();
        return multithreadedTokenBucketFilter;
    }

    void daemonThread() throws InterruptedException {

        while (true) {

            synchronized (this) {
                while (possibleTokens == maxTokens) wait();
                possibleTokens++;
                notifyAll();
            }

            Thread.sleep(ONE_SECOND);
        }
    }

    void getToken() throws InterruptedException {

        synchronized (this) {
            while (possibleTokens == 0) wait();
            possibleTokens--;
            notifyAll();
        }

        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / 1_000);
    }
}
