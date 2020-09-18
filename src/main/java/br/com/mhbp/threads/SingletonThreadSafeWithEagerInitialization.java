package br.com.mhbp.threads;

public class SingletonThreadSafeWithEagerInitialization {

    private static SingletonThreadSafeWithEagerInitialization instance;

    static {
        try {
            instance = new SingletonThreadSafeWithEagerInitialization();
        } catch (Exception e) {
            // Handle exception here
        }
    }

    private SingletonThreadSafeWithEagerInitialization() {
    }

    public static SingletonThreadSafeWithEagerInitialization getInstance() {
        return instance;
    }
}
