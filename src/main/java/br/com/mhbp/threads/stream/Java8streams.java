package br.com.mhbp.threads.stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Java8streams {

    public static void main(String[] args) {

        Stream.of("Hello", "World")
//                .mapToInt(s -> s.length())
                .mapToInt(String::length) // s -> length
                .sum();

        Stream.of("Hello", "World")
//                .forEach(s -> System.out.println(s));
                .forEach(System.out::println);

        Stream.of("Hello", "World")
                .filter( s -> s.contains("H"))
                .forEach(System.out::println);

        LongStream.range(0, 100)
//                .mapToObj(l -> BigInteger.valueOf(l));
                .mapToObj(BigInteger::valueOf);


        LongStream.rangeClosed(0, 100)
                .mapToObj(BigInteger::valueOf)
//        .reduce( (a,b) -> a.multiply(b));
        .reduce( BigInteger::multiply);


        ConcurrentSkipListSet<String> words = Stream.of("asda", "grrgr")
//                .collect(Collectors.toCollection(() ->new ConcurrentSkipListSet<>()));
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));

        IntStream range = IntStream.range(3, 444);

        String[] strings = IntStream.range(3, 444)
                .mapToObj(Integer::toBinaryString)
                .toArray(String[]::new);

        boolean b = Arrays.stream(strings).allMatch(a -> a.length() > 5);

        int[] array = {1,2,3,4,5};
        String[] result = new String[array.length];

        Arrays.setAll(result, i -> "#_" + i);

        words.stream()
                .filter(Objects::nonNull)
                .map(w -> w.length() + "_" + w)
                .collect(Collectors.toList());

        Stream<Node> nodeStream = Stream.of(new Node(1, "first"), new Node(2, "second"), new Node(3, "third"));
        boolean four = nodeStream.map(n -> n.name)
                .anyMatch(s -> s.equals("four"));


        Stream<Node> nodeStream2 = Stream.of(new Node(1, "first"), new Node(2, "second"), new Node(3, "third"));
        nodeStream2.map(n -> n.name)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new ClassCastException(Node.class.getName()));


        Stream<Node> nodeStream3 = Stream.of(new Node(1, "first"), new Node(2, "second"), new Node(3, "third"));
        nodeStream3.map(n -> n.name)
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(System.out::println);


        Stream<Node> nodeStream4 = Stream.of(new Node(1, "first"), new Node(2, "second"), new Node(3, "third"));
        nodeStream4.map(n -> n.name)
                .filter(Objects::nonNull)
                .findFirst()
                .map(s -> s.length() + "_" + s)
                .ifPresent(System.out::println);
//                .orElse("Not found");

        Stream<Node> nodeStream5 = Stream.of(new Node(1, "first"), new Node(2, "second"), new Node(3, "third"));

        nodeStream5.map(n -> n.name);

        /*
        localStream;

        if (..) {
        localStream = .....;
        else {
        localStream = ..............;
        }

        localStream.(,,,)
         */


    }
}

class Node {
    int id;
    String name;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
