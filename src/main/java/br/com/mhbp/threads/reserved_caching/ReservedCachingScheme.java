package br.com.mhbp.threads.reserved_caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

public class ReservedCachingScheme<K, V> {

    private final V reserved;
    private final Map<K, V> cache = new ConcurrentHashMap<>();


    private class ReservedBlocker implements ForkJoinPool.ManagedBlocker {

        private K key;
        private volatile V result;

        public ReservedBlocker(K key) {
            this.key = key;
        }

        @Override
        public boolean block() throws InterruptedException {
            synchronized (cache) {
                while(!isReleasable()) cache.wait();
            }
            return false;
        }

        @Override
        public boolean isReleasable() {
            return (result = cache.get(key)) == reserved;
        }
    }
    
    public ReservedCachingScheme(Supplier<V> reserved) {
        this.reserved = reserved.get();
    }

    public V calculate(K key, Supplier<V> supplier) {
        V result = cache.putIfAbsent(key, reserved);

        if (result == null) {

            result = supplier.get();//in this case  we postpone the computation until we sure that the result is gonna be used it.
            cache.replace(key, reserved, result);

            synchronized (cache) {
                cache.notifyAll();
            }

        } else if (result == reserved) {
            try {
                ReservedBlocker reservedBlocker = new ReservedBlocker(key);
                ForkJoinPool.managedBlock(reservedBlocker);
                result = reservedBlocker.result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
