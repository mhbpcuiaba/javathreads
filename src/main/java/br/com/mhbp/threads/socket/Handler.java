package br.com.mhbp.threads.socket;

import java.io.IOException;

public interface Handler<S> {
    void handle(S s) throws IOException;
}
