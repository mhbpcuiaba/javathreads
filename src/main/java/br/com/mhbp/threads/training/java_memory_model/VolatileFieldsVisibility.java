package br.com.mhbp.threads.training.java_memory_model;


/**
 * happens before
 *
 *  Volatile
 *  Synchronized
 *  Locks
 *  Concurrent collections
 *  Thread operations (join, start)
 *  final fields (special behavior)
 */
public class VolatileFieldsVisibility {

    int a = 0;
    int b = 0;
    int c = 0;
    volatile int x = 0;


    public void writerThread() {
        a = b = c = 1;

        x = 1; //write of x
    }

    public void readerThread() {
        int r2 = x; //read pf x

        int d1 = a;
        int d2 = b;
        int d3 = c;
    }
}
