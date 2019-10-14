package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyChannelHandler implements Handler<SocketChannel> {



    public void handle(SocketChannel sc) throws IOException {

        ByteBuffer buf = ByteBuffer.allocateDirect(80);
        int read = sc.read(buf);

        if (read == -1) {
            sc.close();
            return;
        }

        if (read >0) {
            transmogrify(buf);

            while (buf.hasRemaining()) {
                sc.write(buf);
            }

            buf.compact();
        }
    }

    int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    void transmogrify(ByteBuffer buf) {

//        buf.limit(buf.position());
//        buf.position(0);
        buf.flip();

        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }


    }
}
