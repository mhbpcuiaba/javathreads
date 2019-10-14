package br.com.mhbp.threads.socket.server;

import br.com.mhbp.threads.socket.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

public class SingleThreadedPollingNonBlockingIOServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8388));
        ssc.configureBlocking(false);


        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();

        Collection<SocketChannel> sockets = new ArrayList<>();

        while (true) {
            SocketChannel sc = ssc.accept();//almost always null

            if (sc != null) {
                sockets.add(sc);
                System.out.println("Connected to " + sc);
                sc.configureBlocking(false);
            }
            for (SocketChannel socket : sockets) {
                if ( socket.isConnected()) {
                    handler.handle(socket);
                }
            }

            sockets.removeIf(socket -> !socket.isConnected());
        }

    }
}
