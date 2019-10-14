package br.com.mhbp.threads.socket;

public class ThreadHandler<S> extends UncheckedIOExceptionConverterHandler<S> {

    public ThreadHandler(Handler other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        new Thread(() -> super.handle(s)).start();
    }
}
