package br.com.mhbp.threads.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ExecutorServiceHandler<S> extends DecoratorHandler<S> {


    private final ExecutorService executorService;
    private final Thread.UncaughtExceptionHandler exceptionHandler;

    public ExecutorServiceHandler(Handler other,
                                  ExecutorService executorService,
                                  Thread.UncaughtExceptionHandler exceptionHandler) {
        super(other);
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    public ExecutorServiceHandler(Handler other, ExecutorService executorService) {
        this(other, executorService, (t,e) -> System.out.println("uncaucht: " + t + ". error: " + e));

    }

        @Override
    public void handle(S s) {

        Future<?> future = executorService.submit(new FutureTask(() -> {
            super.handle(s);
            return null;
        }) {
            @Override
            protected void setException(Throwable throwable) {
                exceptionHandler.uncaughtException(Thread.currentThread(), throwable);
            }

            @Override
            protected void done() {
                super.done();
            }
        });



    }
}
