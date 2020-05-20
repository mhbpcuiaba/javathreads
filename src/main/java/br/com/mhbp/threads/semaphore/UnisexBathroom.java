package br.com.mhbp.threads.semaphore;

public class UnisexBathroom {





    public static void runTest() throws InterruptedException {

        final UnisexBathroom unisexBathroom = new UnisexBathroom();

        Thread female1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.femaleUseBathroom("Lisa");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("John");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male2 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Bob");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male3 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Anil");
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread male4 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Wentao");
                } catch (InterruptedException ie) {

                }
            }
        });

        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();

        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();

    }












    static String WOMEN = "women";
    static String MEN = "men";
    static String NONE = "none";

    String inUsedBy = NONE;
    Semaphore numEmployees = new Semaphore(3);
    int ne;
    void maleUseBathroom(String name) throws InterruptedException {

        synchronized(this) {

            while (inUsedBy == WOMEN) wait();
            numEmployees.acquire();
            ne++;
            inUsedBy = MEN;

        }

        useBath(name);

        synchronized (this) {
            ne--;
            if (ne == 0) inUsedBy = NONE;
            numEmployees.release();
        }
    }

    void femaleUseBathroom(String name) throws InterruptedException {

        synchronized(this) {

            while (inUsedBy == MEN) wait();
            numEmployees.acquire();
            ne++;
            inUsedBy = WOMEN;

        }

        useBath(name);

        synchronized (this) {
            ne--;
            if (ne == 0) inUsedBy = NONE;
            numEmployees.release();
        }
    }

    private void useBath(String name) throws InterruptedException {
        System.out.println(name + " gets into batroom");
        Thread.sleep(1_000);
        System.out.println(name + " gets out batroom");
    }
}
