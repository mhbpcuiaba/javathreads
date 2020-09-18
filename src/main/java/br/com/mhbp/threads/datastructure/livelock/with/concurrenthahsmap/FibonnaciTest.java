package br.com.mhbp.threads.datastructure.livelock.with.concurrenthahsmap;

import java.math.BigInteger;

public class FibonnaciTest {

    public static void main(String[] args) {
        Fibonnaci fib = new Fibonnaci();
        BigInteger r = fib.apply(100);
        System.out.println("fib of 100: " + r);
//BigInteger result = cache.computeIfAbsent(n, n1 -> apply(n1 - 1, cache).add(apply(n1 - 2, cache)));
    }
}
