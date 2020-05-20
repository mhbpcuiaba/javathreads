package br.com.mhbp.threads.forkjoinpool;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<BigInteger> {

    private final int n;

    private FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        return f(n);
    }

    public BigInteger f(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        if (n % 2 == 1) {
            int half_n = (n + 1) / 2;
            FibonacciTask fn_1Task= new FibonacciTask(half_n - 1);
            fn_1Task.fork();
            BigInteger fn = f(half_n);
            BigInteger fn_1 = fn_1Task.join();

            return fn_1.multiply(fn_1).add(fn.multiply(fn));
        } else {
            int half_n = n / 2;
            FibonacciTask fn_1Task = new FibonacciTask(half_n - 1);
            fn_1Task.fork();
            BigInteger fn = f(half_n);
            BigInteger fn_1 = fn_1Task.join();
            return fn_1.shiftLeft(1).add(fn).multiply(fn);
        }
    }
}
