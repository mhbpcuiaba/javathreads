package br.com.mhbp.threads.mapreduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            list.add(i);
        }

        list.stream()
                .map(n -> n + 1)
                .forEach(System.out::println);

        System.out.println();


        list.stream()
                .map(n -> Arrays.asList(n - 1, n + 1))
                .forEach(System.out::println);

        System.out.println();
    }

}
