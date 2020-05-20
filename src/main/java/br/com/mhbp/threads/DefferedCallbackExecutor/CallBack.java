package br.com.mhbp.threads.DefferedCallbackExecutor;

public class CallBack {
    long executedAt;
    String message;

    public CallBack(long executedAt, String message) {
        this.executedAt = executedAt;
        this.message = message;
    }

    void call() {

    }
}
