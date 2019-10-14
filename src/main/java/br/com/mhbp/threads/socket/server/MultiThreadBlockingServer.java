package br.com.mhbp.threads.socket.server;

import br.com.mhbp.threads.socket.Handler;
import br.com.mhbp.threads.socket.PrintingHandler;
import br.com.mhbp.threads.socket.ThreadHandler;
import br.com.mhbp.threads.socket.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8387);
        Handler<Object> handler = new ThreadHandler<>(
                new PrintingHandler(
                        new TransmogrifyHandler()
                )
        );

        while (true) {
            Socket s = serverSocket.accept();
            handler.handle(s);
        }
    }
}
