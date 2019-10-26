package br.com.mhbp.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Examples {


    public static void main(String[] args) {


// Using Stream builder()
        Stream.Builder<String> builder = Stream.builder();

        // Adding elements in the stream of Strings
        Stream<String> stream = builder.add("flamengo")
                                    .add("football")
                                    .add("brazil")
                                    .add("java language")
                                    .build();

        // Displaying the elements in the stream
        stream.forEach(System.out::println);

        IntStream streamInt = IntStream.of(4, 5, 6, 7);
        IntSummaryStatistics summary_data = streamInt.summaryStatistics();

        System.out.println(summary_data);

        IntStream stream2 = IntStream.generate(() -> (int)(Math.random() * 10000));

        stream2.limit(7).forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 34, 65, 43, 768, 3, 777, 4);
        long count_int = integers.stream().collect(Collectors.counting());

        System.out.println(count_int);
        integers.stream().peek(System.out::println).count();
        boolean answer = integers.stream().allMatch(n-> n % 3 ==0);
        System.out.println(answer);

        integers.stream().filter(num -> num % 5 == 0).forEach(System.out::println);

        integers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        integers.stream().forEachOrdered(System.out::println);

        Optional<Integer> any = integers.stream().findAny();

        if (any.isPresent()) {
            out.println(any.get());
        } else {
            out.println("no value");
        }

        //stream anymatvh (predicate)

        boolean anyMatch = integers.stream().anyMatch(n -> (n * (n + 1)) / 4 == 5);
        out.println(anyMatch);


        Stream<String> words = Stream.of("Armor", "Best wishes", "java is awsome", "pragmatic programmer");


        words.filter(str -> Character.isUpperCase(str.charAt(1)))
                .forEach(System.out::println);

        words = Stream.of("Boot", "Springboot", "jms", "passioante programmwer");
        boolean answerwords = words.allMatch(str -> str.length() > 2);
        out.println(answerwords);

        words = Stream.of("all in one", "jvm", "garbage collector", "free platform");
        boolean founUppercase = words.anyMatch(word -> Character.isUpperCase(word.charAt(0)));
        out.println(founUppercase);

        words = Stream.of("streams rock", "oh yeah", "fizz buzz", "nice have you here");
        boolean hasWordWithLengthEqualTo4 = words.noneMatch(str -> (str.length() == 4));
        out.println(hasWordWithLengthEqualTo4);

        List<String> list = Arrays.asList("2345", "9255", "10230",  "72110", "15");

        list.stream().mapToLong(num -> Long.parseLong(num))
                .filter(num -> Math.sqrt(num) / 5 == 3 )
                .forEach(out::println);
    }
}
