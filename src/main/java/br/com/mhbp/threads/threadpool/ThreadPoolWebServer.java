package br.com.mhbp.threads.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolWebServer {

    static final int N_THREADS = 100;
    static final int SERVER_PORT = 8080;
    static final ExecutorService es = Executors.newFixedThreadPool(N_THREADS);


    public static void main(String[] args) {

        int[] array = { 34, 31, 34, 77, 82, 80, 81, 87,  81};
        System.out.println(maisComun(array));
    }

    public static int maisComun(int[] array) {

        HashMap<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < array.length; i++) {


            if (count.containsKey(array[i])) {
                Integer integer = count.get(array[i]); // map key is array index
                integer++;
                count.put(array[i], integer);
            } else {
                count.put(array[i], 1);
            }

        }

        int element = array[0];
        int maxCount = count.get(array[0]);

        for (int i = 0; i < array.length; i++) {


            if(count.get(array[i]) > maxCount) {
                element = array[i];
            } else if(count.get(array[i]) == maxCount) {
                if (element > array[i]) {
                    element = array[i];
                }
            }
        }

        return element;
    }






    public static void main2(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Math.sqrt(3.4);

        while (true) {
            Socket accept = serverSocket.accept();
            es.submit(() -> {
                try {
                    Worker.handleRequest(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    static class Worker {
        static void handleRequest(Socket s) throws IOException {
            StringBuffer req = new StringBuffer();

            try (Reader r = new BufferedReader(new InputStreamReader(s.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())));
                 OutputStream os = s.getOutputStream()
                 ) {

                int c;

                while( (c = r.read()) != -1) {
                    req.append((char)c);
                }

                req.append(". This was your request. It will processed later");

                os.write(req.toString().getBytes());
            }
        }
    }
}
