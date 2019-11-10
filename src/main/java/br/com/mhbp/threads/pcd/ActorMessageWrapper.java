package br.com.mhbp.threads.pcd;

public class ActorMessageWrapper {

    private final Object msg;
    private final ActorMessageWrapper next;

    public ActorMessageWrapper(Object msg, ActorMessageWrapper next) {
        this.msg = msg;
        this.next = next;
    }

    public Object getMsg() {
        return msg;
    }

    public ActorMessageWrapper getNext() {
        return next;
    }
}
