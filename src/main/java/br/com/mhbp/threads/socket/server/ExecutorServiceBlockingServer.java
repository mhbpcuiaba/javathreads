package br.com.mhbp.threads.socket.server;

import br.com.mhbp.threads.socket.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8388);

        Handler<Object> handler = new ThreadHandler<>(
                new ExecutorServiceHandler(
                        new PrintingHandler(
                                new TransmogrifyHandler()
                        ),
                        Executors.newFixedThreadPool(10),
                        (t, e) -> {
                            System.out.println("uncaught: " +t + "exception: " + e);
                        }
                )
        );

        while (true) {
            Socket s = serverSocket.accept();
            handler.handle(s);
        }

    }
}
