package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.net.Socket;

public class NastyChump {

    public static void main(String[] args) throws InterruptedException {
        Socket[] socket = new Socket[3000];

        for (int i = 0; i < socket.length; i++) {
            try {
                socket[i] = new Socket("localhost", 8388);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100_00);
    }
}
