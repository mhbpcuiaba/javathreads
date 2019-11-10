package br.com.mhbp.threads.futures;

import java.util.concurrent.*;

public class AsyncResponseWrapper<V> implements Future<V> {

    public static final int BLOCKING_INDEFINITELY = 0;
    private V value;
    private Exception executionException;
    private boolean isCompletedExceptionally = false;
    private boolean isCancelled = false;
    private boolean isDone = false;
    private long checkCompletedInterval = 100;

    public AsyncResponseWrapper() {

    }

    public AsyncResponseWrapper(V val) {
        this.value = val;
        this.isDone = true;
    }

    public AsyncResponseWrapper(Throwable ex) {
        this.executionException = new ExecutionException(ex);
        this.isCompletedExceptionally = true;
        this.isDone = true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isCancelled = true;
        this.isDone = true;
        //ignore mayInterruptIfRunning because it cannot interrrupt when is running
        return false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    public boolean isNotCancelled() {
        return !isCancelled;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    public boolean isNotDone() {
        return !isDone;
    }
    @Override
    public V get() throws InterruptedException, ExecutionException {
        block(BLOCKING_INDEFINITELY);

        return innerGet();
    }

    private V innerGet() throws ExecutionException, InterruptedException {
        if (isCancelled) {
            throw  new CancellationException();
        }

        if (isCompletedExceptionally) {
            throw  new ExecutionException(executionException);
        }
        if (isDone) {
            return  value;
        }
        throw  new InterruptedException();
    }

    @Override
    public V get(long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        long timeoutMillis = timeUnit.toMillis(timeout);
        block(timeoutMillis);
        return innerGet();
    }

    public void setCheckCompletedInterval(long millis) {
        this.checkCompletedInterval = millis;
    }

    public boolean complete(V val) {
        this.value = val;
        this.isDone = true;
        return true;
    }

    public boolean completeExceptionally(Throwable ex) {
        this.value = null;
        this.executionException = new ExecutionException(ex);
        this.isCompletedExceptionally = true;
        this.isDone = true;
        return true;
    }

    private void block(long timeout) throws InterruptedException {
        long start = System.currentTimeMillis();

        while(isNotDone() && isNotCancelled()) {

            if ( timeout > 0) {
                long now = System.currentTimeMillis();
                if (now > start + timeout) {
                    break;
                }
            }
            Thread.sleep(checkCompletedInterval);
        }

    }
}
