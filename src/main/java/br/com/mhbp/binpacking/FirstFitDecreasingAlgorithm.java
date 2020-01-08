package br.com.mhbp.binpacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstFitDecreasingAlgorithm {

    List<Bin> bins;
    List<Integer> items;
    int binCapacity;
    int binCounter;

    public FirstFitDecreasingAlgorithm(List<Integer> items, int binCapacity) {

        this.items = items;
        this.binCapacity = binCapacity;
        this.bins = new ArrayList<>(items.size());
    }

    public void solveBinPackingProblem() {

        Collections.sort(items, Collections.reverseOrder());

        if (this.items.get(0) > this.binCapacity) {
            System.out.println("No feasible solution...");
            return;
        }

        this.bins.add(new Bin(binCapacity, binCounter));

        for (Integer currentItem : items) {

            boolean isOk = false;
            int currentBin = 0;

            while (!isOk) {

                if (currentBin == bins.size()) {
                    Bin bin = new Bin(binCapacity, ++binCounter);
                    bin.put(currentItem);//? what about the returing value??
                    bins.add(bin);
                    isOk = true;
                } else if ( this.bins.get(currentBin).put(currentItem)) {
                    isOk = true;
                } else {
                    currentBin++;
                }
            }
        }
    }

    public void showResults() {
        bins.forEach(System.out::println);
    }
}
