package br.com.mhbp.threads.skirental;

import java.util.concurrent.Semaphore;

public class SkisForRent {

    public static final Integer maxSkiPairs = 150;
    private static final Integer minInStock = 5;
    private final Semaphore semaphore = new Semaphore(maxSkiPairs - minInStock);
    private SkiPair[] inventory;

    public SkisForRent() {
        inventory = new SkiPair[maxSkiPairs];
        for (int i = 0; i < maxSkiPairs; i++) {
            inventory[i] = new SkiPair("skiPair-" + i, false);
        }
    }


    public SkiPair rent() throws InterruptedException {
        semaphore.acquire();
        return  getSkiPair();
    }

    public void returning(SkiPair skiPair) {
        if (isReturnable(skiPair)) {
            semaphore.release();
        }

    }

    private synchronized boolean isReturnable(SkiPair skiPair) {
        if(skiPair.inUse) {
            skiPair.inUse = Boolean.FALSE;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private synchronized SkiPair getSkiPair() {

        for (SkiPair skiPair : inventory) {

            if (!skiPair.inUse) {
                skiPair.inUse = Boolean.TRUE;
                return  skiPair;
            }
        }

        return null;
    }
}
