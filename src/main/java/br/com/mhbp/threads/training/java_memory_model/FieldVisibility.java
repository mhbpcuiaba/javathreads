package br.com.mhbp.threads.training.java_memory_model;

public class FieldVisibility {

    int x = 0;

    public void writerThread() {
        x = 1;
    }


    public void readerThread() {
        int r2 = x;
        System.out.println(r2);
    }
}
