package br.com.mhbp.threads.socket.server;

import br.com.mhbp.threads.socket.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

public class BlockingIOServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8388));



        Handler<SocketChannel> handler = new ThreadHandler<>(
                new ExecutorServiceHandler(
                        new PrintingHandler(
                           new BlockingChannelHandler(
                                new TransmogrifyChannelHandler()
                           )
                        ),
                        Executors.newFixedThreadPool(10)
                )
        );

        while (true) {
            SocketChannel sc = ssc.accept();
            handler.handle(sc);
        }

    }
}
