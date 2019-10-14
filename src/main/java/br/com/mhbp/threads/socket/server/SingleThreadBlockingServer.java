package br.com.mhbp.threads.socket.server;

import br.com.mhbp.threads.socket.PrintingHandler;
import br.com.mhbp.threads.socket.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class SingleThreadBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8284);

        new PrintingHandler<>(
                new TransmogrifyHandler()
        );
    }
}
