package br.com.mhbp.binpacking;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(10, 5, 5);
        int binCapacity = 4;

        FirstFitDecreasingAlgorithm firstFitDecreasingAlgorithm = new FirstFitDecreasingAlgorithm(items, binCapacity);
        firstFitDecreasingAlgorithm.solveBinPackingProblem();
        firstFitDecreasingAlgorithm.showResults();
    }
}
