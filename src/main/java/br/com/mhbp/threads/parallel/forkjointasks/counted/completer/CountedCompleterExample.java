package br.com.mhbp.threads.parallel.forkjointasks.counted.completer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;

public class CountedCompleterExample {

    public static void main(String[] args) {

        List<BigInteger> list = new ArrayList<>();
        for (int i = 3; i <  20; i++) {
            list.add(new BigInteger(Integer.toString(i)));
        }


        ForkJoinPool.commonPool().invoke(new FactorialTask(null, list));

    }

    static class FactorialTask extends CountedCompleter<Void> {

        private static int SEQUENTIAL_THRESHOLD = 5;
        private List<BigInteger> integerList;
        private int numberCalculated;

        private FactorialTask (CountedCompleter<Void> parent, List<BigInteger> integerList) {
            super(parent);
            this.integerList = integerList;
        }

        @Override
        public void compute() {

            if (integerList.size() <= SEQUENTIAL_THRESHOLD) {
                showFactorial();
            } else {
                int NUMBER_OF_TASKS = 2;
                int middle = integerList.size() / 2;
                List<BigInteger> rightList = integerList.subList(middle, integerList.size());
                List<BigInteger> leftList = integerList.subList(0, middle);
                addToPendingCount(NUMBER_OF_TASKS);
                FactorialTask factorialTaskRightList = new FactorialTask(this, rightList);
                FactorialTask factorialTaskLeftList = new FactorialTask(this, leftList);

                factorialTaskRightList.fork();
                factorialTaskLeftList.fork();
            }
            tryComplete();


        }



        @Override
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            if (countedCompleter == this) {
                System.out.printf("completed thread : %s numberCalculated=%s%n", Thread
                        .currentThread().getName(), numberCalculated);
            }
        }

        private void showFactorial () {

            for (BigInteger i : integerList) {
                BigInteger factorial = CalcUtil.calculateFactorial(i);
                System.out.printf("%s! = %s, thread = %s%n", i, factorial, Thread
                        .currentThread().getName());
                numberCalculated++;
            }
        }
    }

    private static class FactorialTaskWithReturing extends CountedCompleter<BigInteger> {

        private static int SEQUENTIAL_THRESHOLD = 5;
        private List<BigInteger> integerList;
        private AtomicReference<BigInteger> result;

        private FactorialTaskWithReturing(CountedCompleter<BigInteger> parent,
                              AtomicReference<BigInteger> result,
                              List<BigInteger> integerList) {
            super(parent);
            this.integerList = integerList;
            this.result = result;
        }

        @Override
        public BigInteger getRawResult() {
            return result.get();
        }

        @Override
        public void compute() {

            while (integerList.size() > SEQUENTIAL_THRESHOLD) {
                int size = integerList.size();
                List<BigInteger> remaining = integerList.subList(0, size - SEQUENTIAL_THRESHOLD);
                List<BigInteger> toBeProcessed = integerList.subList(size - SEQUENTIAL_THRESHOLD, size);
                addToPendingCount(1);
                FactorialTaskWithReturing factorialTaskWithReturing = new FactorialTaskWithReturing(this, result, toBeProcessed);
                factorialTaskWithReturing.fork();
            }

            sumFactorials();
            propagateCompletion();
        }

        private void sumFactorials () {
            for (BigInteger factorial : integerList) {
                result.getAndAccumulate(CalcUtil.calculateFactorial(factorial), (b1, b2) -> b1.add(b2));
            }
        }
    }

}
