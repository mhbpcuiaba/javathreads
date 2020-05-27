package br.com.mhbp.threads.rate.limiting.using.token.bucket.filter;

import java.util.HashSet;
import java.util.Set;


/*     Follow-up exercises
*      Grant tokens to threads in a FIFO order
       Generalize the solution for any rate of token generation
* */

public class TokenBucketFilter {

    private int maxTokens;
    private long lastRequestTime = System.currentTimeMillis();
    long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    synchronized void getToken() throws InterruptedException {
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1_000;

        if (possibleTokens > maxTokens) {
            possibleTokens = maxTokens;
        }

        if (possibleTokens == 0) {
            Thread.sleep(1_000);
        } else {
            possibleTokens--;
        }

        lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1_000));
    }

    public static void main(String[] args) throws InterruptedException {

        Set<Thread> allThreads = new HashSet<Thread>();
        final TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(5);

        Thread.sleep(10_000);

        for (int i = 0; i < 12; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException ie) {
                        System.out.println("We have a problem");
                    }
                }
            });
            thread.setName("Thread_" + (i + 1));
            allThreads.add(thread);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
