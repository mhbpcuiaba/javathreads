package br.com.mhbp.threads.parallel.forkjointasks.counted.completer;

import java.math.BigInteger;

public class CalcUtil {
    public static BigInteger calculateFactorial(BigInteger i) {

        if (i.equals(1)) {
            return i;
        } else {
            BigInteger subtract = i.subtract(BigInteger.valueOf(1l));
            return i.multiply(calculateFactorial(subtract));
        }
    }
}
