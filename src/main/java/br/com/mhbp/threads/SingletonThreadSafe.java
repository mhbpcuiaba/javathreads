package br.com.mhbp.threads;

public class SingletonThreadSafe {
    private volatile static SingletonThreadSafe instance;

    private SingletonThreadSafe() {

    }

    public static SingletonThreadSafe getInstance() {

        // Check if object is uninitialized
        if (instance == null) {

            // Now synchronize on the class object, so that only
            // 1 thread gets a chance to initialize the instance
            // object. Note that multiple threads can actually find
            // the superman object to be null and fall into the
            // first if clause
            synchronized (SingletonThreadSafe.class) {

                // Must check once more if the instance object is still
                // null. It is possible that another thread might have
                // initialized it already as multiple threads could have
                // made past the first if check.
                if (instance == null) {
                    instance = new SingletonThreadSafe();
                }
            }

        }

        return instance;
    }
}
