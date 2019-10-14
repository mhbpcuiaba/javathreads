package br.com.mhbp.threads.socket;

import java.io.IOException;

public abstract class DecoratorHandler<S> implements Handler<S> {


    protected Handler<S> other;

    public DecoratorHandler(Handler other) {
        this.other = other;
    }

    public void handle(S s) throws IOException {
        other.handle(s);
    }
}
