package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

public class ReadHandler implements Handler<SelectionKey> {
    private Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public ReadHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {

        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        ByteBuffer buf = ByteBuffer.allocateDirect(80);
        int read = sc.read(buf);

        if (read == -1) {
            pendingData.remove(sc);
            sc.close();
            System.out.println("Disconnected from " + sc + " IN_READ");
            return;
        }

        if (read > 0) {
            transmogrify(buf);
            pendingData.get(sc).add(buf);
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }

    }

    void transmogrify(ByteBuffer buf) {
        //        buf.limit(buf.position());        buf.position(0);
        buf.flip();

        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }
    }

    int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
