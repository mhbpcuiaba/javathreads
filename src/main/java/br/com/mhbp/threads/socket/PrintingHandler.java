package br.com.mhbp.threads.socket;

import java.io.IOException;

public class PrintingHandler<S> extends DecoratorHandler<S> {

    protected PrintingHandler(Handler other) {
        super(other);
    }

    @Override
    public void handle(S s) throws IOException {
        System.out.println("Connected to " + s);
        try {
            other.handle(s);
        } finally {

            System.out.println("Disconnected from " + s);
        }
    }
}
