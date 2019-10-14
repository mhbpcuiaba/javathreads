package br.com.mhbp.threads.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class WriteHandler implements Handler<SelectionKey> {
    private Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public WriteHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {

        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        Queue<ByteBuffer> queue = pendingData.getOrDefault(sc, new ArrayDeque<>());

        while (!queue.isEmpty()) {
            ByteBuffer byteBuffer = queue.peek();
            int written = sc.write(byteBuffer);

            if (written == -1) {
                sc.close();
                System.out.println("Disconnected from " + sc + " IN_WRITE");
                pendingData.remove(sc);
                return;
            }

            if (byteBuffer.hasRemaining()) {
                return;
            }
            queue.remove();
        }
        selectionKey.interestOps(SelectionKey.OP_READ);


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
