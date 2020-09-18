package br.com.mhbp.threads.live.stream.heinz.kabutz.session1;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReadWriteLock rwlock = new ReentrantReadWriteLock();

        Runnable rTask = () -> {
            for (int i = 0; i < 10; i++) {

                rwlock.readLock().lock();

                try {
                    sleepQuietly(1_000);
                } finally {
                    rwlock.readLock().unlock();
                }
            }
        };

        AtomicBoolean bool = new AtomicBoolean(false);
        Runnable wTask =  () -> {
            rwlock.writeLock().lock();

            try {
                System.out.println("caught write lock");
            } finally {
                rwlock.writeLock().unlock();
            }
            bool.set(true);
        };
    }

    private static void sleepQuietly(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw  new CancellationException("interrupted");
        }
    }
}
