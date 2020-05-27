package br.com.mhbp.threads.phaser;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPhaser {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.invoke(new PhasedAction(100, new Phaser(100)));
        System.out.println(fjp);

    }

    private static class PhasedAction extends RecursiveAction {

        private final int phases;
        private final Phaser ph;

        private PhasedAction(int phases, Phaser ph) {
            this.phases = phases;
            this.ph = ph;
        }

        @Override
        protected void compute() {

            if (phases == 1) {
                System.out.printf("wait: %s%n", Thread.currentThread());
                ph.arriveAndAwaitAdvance();
                System.out.printf("done: %s%n", Thread.currentThread());
            } else {
                int left = phases / 2;
                int right = phases - left;
                invokeAll(new PhasedAction(left, ph), new PhasedAction(right, ph));
            }
        }
    }
}
