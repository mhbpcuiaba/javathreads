package br.com.mhbp.threads.datastructure.livelock.with.concurrenthahsmap;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Fibonnaci implements Function<Integer, BigInteger> {

    @Override
    public BigInteger apply(Integer n) {
        Map<Integer, BigInteger> cache = new ConcurrentHashMap<>();
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
        return apply(n, cache);
    }

    public BigInteger apply(int n, Map<Integer, BigInteger> cache) {
        /**
        ConcurrentHashMap does not work well when you try to change it inside a function or bifunction parameter
         EX:
                default V computeIfAbsent(K var1, Function<? super K, ? extends V> var2)
                default V computeIfPresent(K var1, BiFunction<? super K, ? super V, ? extends V> var2)
         */
        return cache.computeIfAbsent(n, n1 -> apply(n - 1, cache).add(apply(n - 2, cache)));
    }

}
