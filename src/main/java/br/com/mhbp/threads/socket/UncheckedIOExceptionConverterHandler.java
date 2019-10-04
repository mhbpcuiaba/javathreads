package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> extends DecoratorHandler<S> {


    protected UncheckedIOExceptionConverterHandler(Handler other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        try {
            super.handle(s);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
