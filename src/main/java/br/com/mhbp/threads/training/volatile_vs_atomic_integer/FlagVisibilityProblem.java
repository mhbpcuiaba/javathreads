package br.com.mhbp.threads.training.volatile_vs_atomic_integer;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FlagVisibilityProblem {

    AtomicInteger ai;
    public static void main(String[] args) {

        FlagVisibilityProblem flagVisibilityProblem = new FlagVisibilityProblem();

        Executors.newFixedThreadPool(10).submit(() -> {
            flagVisibilityProblem.toggle();
            System.out.println(flagVisibilityProblem.flag());
        });



    }
    boolean flag;


    public void toggle() {
        flag = !flag;
    }

    public boolean flag () {
        return flag;
    }
}
