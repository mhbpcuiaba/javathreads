package br.com.mhbp.threads.socket;

import br.com.mhbp.threads.socket.DecoratorHandler;
import br.com.mhbp.threads.socket.Handler;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class BlockingChannelHandler extends DecoratorHandler<SocketChannel> {


    public BlockingChannelHandler(Handler<SocketChannel> other) {
        super(other);
    }

    @Override
    public void handle(SocketChannel sc) throws IOException {

        while(sc.isConnected()) {
            super.handle(sc);
        }

    }
}
