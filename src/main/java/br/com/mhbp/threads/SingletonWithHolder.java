package br.com.mhbp.threads;

public class SingletonWithHolder {
    private SingletonWithHolder() {
    }

    private static class Holder {
        private static final SingletonWithHolder instance = new SingletonWithHolder();
    }

    public static SingletonWithHolder getInstance() {
        return Holder.instance;
    }

    public void getConnection() {
        System.out.println("here you are.. your connection...");
    }
}
