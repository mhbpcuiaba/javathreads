package br.com.mhbp.threads.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupingPartitioningExamples {


    public static void main(String[] args) {

        List<Person> persons = generate();

        Map<Boolean, List<Person>> us = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.country.equals("US")));

        System.out.println(us);

        Map<String, List<Person>> us1 = persons.stream()
                .collect(Collectors.groupingBy(p -> p.country));

        System.out.println(us1);

        Map<String, Long> us2 = persons.stream()
                .collect(Collectors.groupingBy(p -> p.country, Collectors.counting()));

        System.out.println(us2);

        Map<Boolean, Long> us3 = persons.stream()
                .collect(Collectors.partitioningBy(p -> p.country.equals("US"), Collectors.counting()));

        Map<String, Long> collect = persons
                .stream()
                .collect(Collectors.groupingBy((Person p) -> p.country, Collectors.counting()));


    }

    static List<Person> generate() {
        ArrayList<Person> array = new ArrayList<>();
        array.add(new Person("person01", "US"));
        array.add(new Person("person02", "US"));
        array.add(new Person("person03", "Brazil"));
        array.add(new Person("person04", "US"));
        array.add(new Person("person05", "Brazil"));
        array.add(new Person("person06", "US"));
        array.add(new Person("person07", "Germany"));
        array.add(new Person("person08", "US"));
        return array;
    }

    static class Person {
        String name;
        String country;

        public Person(String name, String country) {
            this.name = name;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
