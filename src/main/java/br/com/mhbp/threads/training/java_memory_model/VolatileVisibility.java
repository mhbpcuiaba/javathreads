package br.com.mhbp.threads.training.java_memory_model;


/**
 *
 Out of order execution

 Performance driven changfes done by Compiler, JVM or CPU

 Field Visibility

 In presence of multiple threads a.k.a Concurrency




 JMM is a specification which guarantees visibility of fields (aka happens before) amidst reordering og instructions.

 */
public class VolatileVisibility {

    volatile int x = 0;

    public void writerThread() {
        x = 1;
    }

    public void readerThread() {
        int r2 = x;
    }
}
