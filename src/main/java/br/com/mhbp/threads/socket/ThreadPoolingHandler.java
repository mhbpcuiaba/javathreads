package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadPoolingHandler<S> extends UncheckedIOExceptionConverterHandler<S> {


    private final ExecutorService executorService;
    private final Thread.UncaughtExceptionHandler exceptionHandler;

    protected ThreadPoolingHandler(Handler other,
                                   ExecutorService executorService,
                                   Thread.UncaughtExceptionHandler exceptionHandler) {
        super(other);
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
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

        });


//        executorService.execute(() -> {
//            try {
//                other.handle(s);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
