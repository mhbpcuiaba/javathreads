package br.com.mhbp.threads.semaphore;

public class ReadLock {


    public static void main(String[] args) throws InterruptedException {
        ReadLock readLock = new ReadLock();

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("attempting to acquire write lock in t1:" + System.currentTimeMillis());
                readLock.acquireWriteLock();
                System.out.println("write lock acquired t1:" + System.currentTimeMillis());

                while (true) {
                    Thread.sleep(500);
                }
            } catch (Exception e) {

            }
        });


        Thread t2 = new Thread(() -> {
            try {
                System.out.println("attempting to acquire write lock in t2:" + System.currentTimeMillis());
                readLock.acquireWriteLock();
                System.out.println("write lock acquired t2:" + System.currentTimeMillis());

            } catch (Exception e) {

            }
        });


        Thread r1 = new Thread(() -> {
            try {
                System.out.println("r1 lock acquired at:" + System.currentTimeMillis());
                System.out.println("r1 lock released at:" + System.currentTimeMillis());
            }catch (Exception e) {}
        });

        Thread r2 = new Thread(() -> {
            try {
                readLock.releaseReadLock();
                readLock.acquireReadLock();
            }catch (Exception e) {}
        });

        r1.start();
        t1.start();
        Thread.sleep(3000);
        r2.start();
        Thread.sleep(1000);
        t2.start();
        r1.join();
        r2.join();
        t2.join();

    }
    int writeLocks;
    int readLocks;
    int readers = 5;
    boolean isWriteLocked = false;

    synchronized void acquireReadLock() throws InterruptedException {


        while (isWriteLocked) wait();

        readers++;
//        while (readers >= readers)

    }

    synchronized void releaseReadLock() {
        readers--;
        notifyAll();
    }


    synchronized void acquireWriteLock() throws InterruptedException {

        while (isWriteLocked && readers > 0) wait();
        isWriteLocked = true;
    }

    synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notifyAll();
    }




}
