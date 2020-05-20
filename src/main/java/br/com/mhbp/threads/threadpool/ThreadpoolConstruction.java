package br.com.mhbp.threads.threadpool;

public class ThreadpoolConstruction {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1_000];

        for (int j = 0; j < threads.length; j++) {

            long time = System.currentTimeMillis();

            for (int i = 0; i < threads.length; i++) {


                threads[i] = new Thread();
                threads[i].start();

            }

            time = System.currentTimeMillis() - time;
            System.out.println("time = " + time + "ms");

            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }

            Object c;
        }


    }
}
