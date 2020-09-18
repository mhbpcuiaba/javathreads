package br.com.mhbp.threads.uber;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberSeatingProblem {
    private int republicans = 0;
    private int democrats = 0;
    CyclicBarrier barrier = new CyclicBarrier(4);
    ReentrantLock locker = new ReentrantLock();

    Semaphore democratsWaiting = new Semaphore(3);
    Semaphore republicansWaiting = new Semaphore(3);

    void interSeat(int numCurrentTypePassenger, Semaphore semaphoreCurrentTyPassenger, int numOtherTypePassenger, Semaphore semaphoreOtherTypePassenger) {

    }
    void seatDemocrat() throws InterruptedException, BrokenBarrierException {

        boolean rideLeader = false;
        locker.lock();
        democrats++;

        if (democrats == 4) {
            democratsWaiting.release(3);
            democrats -= 4;
            rideLeader = true;
        } else if (democrats == 2 && republicans >= 2) {
            democratsWaiting.release(1);
            republicansWaiting.release(2);
            rideLeader = true;
            republicans -= 2;
            democrats -= 2;
        } else {
            locker.unlock();
            democratsWaiting.acquire();
        }

        seat();
        barrier.await();

        if (rideLeader) {
            drive();
            locker.unlock();
        }
    }

    void seatRepublican() throws InterruptedException, BrokenBarrierException {

        boolean rideLeader = false;
        locker.lock();
        republicans++;

        if (republicans == 4) {
            republicansWaiting.release(3);
            republicans -= 4;
            rideLeader = true;
        } else if (democrats == 2 && republicans >= 2) {
            republicansWaiting.release(1);
            democratsWaiting .release(2);
            rideLeader = true;
            republicans -= 2;
            democrats -= 2;
        } else {
            locker.unlock();
            republicansWaiting.acquire();
        }

        seat();
        barrier.await();

        if (rideLeader) {
            drive();
            locker.unlock();
        }
    }

    void seat() {
        System.out.println(Thread.currentThread().getName() + "  seated");
        System.out.flush();
    }

    void drive() {
        System.out.println("Uber Ride on Its wayyyy... with ride leader " + Thread.currentThread().getName());
        System.out.flush();
    }

    public static void runTest() throws InterruptedException {


        final UberSeatingProblem uberSeatingProblem = new UberSeatingProblem();
        Set<Thread> allThreads = new HashSet<Thread>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        uberSeatingProblem.seatDemocrat();
                    } catch (InterruptedException ie) {
                        System.out.println("We have a problem");

                    } catch (BrokenBarrierException bbe) {
                        System.out.println("We have a problem");
                    }

                }
            });
            thread.setName("Democrat_" + (i + 1));
            allThreads.add(thread);

            Thread.sleep(50);
        }

        for (int i = 0; i < 14; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        uberSeatingProblem.seatRepublican();
                    } catch (InterruptedException ie) {
                        System.out.println("We have a problem");

                    } catch (BrokenBarrierException bbe) {
                        System.out.println("We have a problem");
                    }
                }
            });
            thread.setName("Republican_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
