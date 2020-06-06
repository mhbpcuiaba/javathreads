package br.com.mhbp.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiningPhilosophers {

    Semaphore[] forks = new Semaphore[5];
    private Semaphore maxDiners = new Semaphore(4);
    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
    }

    void lifecyle(int philosopherId) throws InterruptedException {
        while(true) {
         think();
         eat(philosopherId);
        }
    }

    private void eat(int philosopherId) throws InterruptedException {
        maxDiners.acquire();
        forks[philosopherId].acquire();
        forks[ (philosopherId + 1) % 5].acquire();

        System.out.println("eating...");
        TimeUnit.SECONDS.sleep(5);
        forks[ (philosopherId + 1) % 5].release();
        forks[philosopherId].release();
        maxDiners.release();
    }


    private void think() throws InterruptedException {
        System.out.println("Thinking...");
        TimeUnit.SECONDS.sleep(2);
    }
}
