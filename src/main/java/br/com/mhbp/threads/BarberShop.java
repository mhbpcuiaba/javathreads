package br.com.mhbp.threads;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
* challeggen many barber
 */
public class BarberShop {

    Semaphore[] chairs;
    boolean[] busyChair;
    Semaphore waitForCustomer = new Semaphore(0);
    Semaphore waitForBarberGetReady = new Semaphore(0);
    Semaphore waitForBarberCutHair = new Semaphore(0);
    Semaphore waitForCustomerLeave = new Semaphore(0);

    int countChars;
    ReentrantLock locker = new ReentrantLock();
    public BarberShop(int numOfChairs) {
        chairs = new Semaphore[numOfChairs];
    }


    void leaveCustomer() throws InterruptedException {
        waitForCustomerLeave.release();//wait for customer to leave barber chair
        waitForBarberCutHair.release();//let customer thread know, haircut is done

    }
    void arriveCustomer() throws InterruptedException {

        locker.lock();

         if (countChars <= chairs.length) {
            locker.unlock();
            return;
         }
         countChars++;
         locker.unlock();

        waitForCustomer.release();
        waitForBarberGetReady.acquire();
        waitForBarberCutHair.acquire();
        waitForCustomerLeave.release();
    }

    void barber() throws InterruptedException {

        while (true) {
            waitForCustomer.acquire();
            waitForBarberGetReady.release();

            System.out.println("cutting hair");
            TimeUnit.SECONDS.sleep(3);
            waitForBarberCutHair.release();//let customer thread know, haircut is done
            waitForCustomerLeave.acquire();//wait for customer to leave barber chair

        }
    }
}
