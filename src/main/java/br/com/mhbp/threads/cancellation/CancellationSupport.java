package br.com.mhbp.threads.cancellation;

import java.util.concurrent.CancellationException;

public class CancellationSupport {

    private static void cancelIfInterrupted() {
        if (Thread.currentThread().isInterrupted()) throw new CancellationException("interrupted");
    }

    public static void cancelIfInterrupted(double d) {
        cancelIfInterrupted();
    }
    public static void cancelIfInterrupted(long d) {
        cancelIfInterrupted();
    }
    public static void cancelIfInterrupted(int d) {
        cancelIfInterrupted();
    }
    public static <T> void cancelIfInterrupted(T d) {
        cancelIfInterrupted();
    }
}
