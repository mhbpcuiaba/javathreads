package br.com.mhbp.threads.parallel.phaser;

import java.util.List;
import java.util.concurrent.Phaser;

public class PhaserExampleOracle {
    private static final int TASKS_PER_PHASER = 10;

    /*
      A Phaser may be used instead of a CountDownLatch to control a one-shot action serving a variable number of
      parties. The typical idiom is for the method setting this up to first register, then start the actions,
      then deregister, as in:
    * */

    void runTasks(List<Runnable> tasks) {
        final Phaser phaser = new Phaser(1); // "1" to register self
        // create and start threads
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }

    /*
    One way to cause a set of threads to repeatedly perform actions for a given number of iterations is to override
    onAdvance:


    * */

    void startTasks(List<Runnable> tasks, final int iterations) {
        final Phaser phaser = new Phaser() {
            protected boolean onAdvance(int phase, int registeredParties) {
                return phase >= iterations || registeredParties == 0;
            }
        };
        phaser.register();
        //if the main task must later await termination, it may re-register and then execute a similar while loop:
        while (!phaser.isTerminated())
            phaser.arriveAndAwaitAdvance();


        for (final Runnable task : tasks) {
            phaser.register();

            new Thread(() -> {
                do {
                    task.run();
                    phaser.arriveAndAwaitAdvance();
                } while (!phaser.isTerminated());
            }).start();
        }
        phaser.arriveAndDeregister(); // deregister self, don't wait
    }

    /*
    To create a set of n tasks using a tree of phasers, you could use code of the following form,
     assuming a Task class with a constructor accepting a Phaser that it registers with upon construction.
     After invocation of build(new Task[n], 0, n, new Phaser()), these tasks could then be started,
     for example by submitting to a pool:
     */

    void build(Task[] tasks, int lo, int hi, Phaser ph) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(tasks, i, j, new Phaser(ph));
            }
        } else {
            for (int i = lo; i < hi; ++i)
                tasks[i] = new Task(ph);
            // assumes new Task(ph) performs ph.register()
        }
    }

}
