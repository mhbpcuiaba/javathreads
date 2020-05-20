package br.com.mhbp.threads.stream.course;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSandbox {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("qweqw", "pokp", "klhjkhk", "ertyuimnmnvd");

        stream.collect(Collectors.toList()).removeIf(s -> s.length() > 2);

        ThreadLocalRandom.current().nextInt();

        /*
        Comparator<Long> longComparator = (Long a, Long b) -> {
            return Long.compare(a, b);
        };
        */

        Comparator<Long> longComparator = Long::compare;
        System.out.println(longComparator);

        List<Catetory> categories = Stream.of(new Catetory("1"), new Catetory("2"), new Catetory("3")).collect(Collectors.toList());
        Collections.sort(categories, Comparator.comparingInt(
                (Catetory cat) -> Integer.parseInt(cat.getCategoryID())
        ).thenComparing(c -> c.getId()));

        Catetory[] categroies = {new Catetory("1"), new Catetory("2"), new Catetory("3")};
        String[] strings = new String[categroies.length];

        Arrays.setAll(strings, i -> categroies[i].getCategoryID());


        Supplier<Stream<String>> streamSupplier  = () -> Stream.of("A", "B", "C", "D");

        Optional<String> result1 = streamSupplier.get().findAny();
        System.out.println(result1.get());

        Optional<String> result2 = streamSupplier.get().findFirst();
        System.out.println(result2.get());
        HashMap hashMap = new HashMap();
        hashMap.getOrDefault(1, -1);
        hashMap.putIfAbsent(2, 2);


    }
}


class Catetory {
    String categoryID;

    Integer id;
    public String getCategoryID() {
        return categoryID;
    }

    public Catetory(String categoryID) {
        this.categoryID = categoryID;
    }

    public Catetory(String categoryID,  Integer id) {
        this.categoryID = categoryID;
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
