package br.com.mhbp.threads.socket;

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
