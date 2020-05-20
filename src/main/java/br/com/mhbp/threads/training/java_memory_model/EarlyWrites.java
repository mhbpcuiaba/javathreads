package br.com.mhbp.threads.training.java_memory_model;


/**
 * JMM is allowed to reordering of statements
 *
 * Java cannot move writes out of synchronized,
 * it means it cannot decrease scope
 * can only increase de scope.
 *
 * W to R . CANNOT
 * R to W. CAN
 *
 * keyword volatile prevents early writes
 *
 * happens-before edge from a write to a volatile variable v to
 * all subsequent reads of v by any thread (where subsequent is defined
 * according to the synchronization order)
 *
 *
 *
 * JSR 133 - JMM
 *
 * VM implementers are encouraged to avoid splitting their 64-bit values where possible. Programmers are encouraged
 * to declare shared 64-bit values as volatile or synchronize their programs correctly to avoid this.
 *
 *too much synchronization gives contention. even with more CPU performance does not improve
 *
 * Lack of synchronization leads to corrupt data, fields might be wrritten early, changes to shared fields might not be visible
 *
 * faster hardware, latent defects show up more frequently. instead of once a year, now once a week
 *
 *
 * Two threads want what the other has and are not willing to part with what they already have
 *
 *
 */
public class EarlyWrites {

    private int x;// int takes one word, long takes 2 words. so when invert writes, we could get one word for each two threads
    private int y;

    public void m1() {
        int i = x;
        y = 100;
    }

    public void m2() {
        int j = y;
        x = 400;
    }
}
