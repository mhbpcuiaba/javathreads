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
/*
Hello guys,
my name is Marcos Henrique Barros Pinto,
I'm from Brazil, my hometown is Cuiaba, I've been living in Sau Paulo more than ten years.

I am Bachelor of  Computer Science at Federal University  of Mato Grosso do Sul, Brazil – 2006.
I am a software architect at Prevent Senior, it is a health care provider focused on seniors.
I found concurrency, parallelism, and distributed system my passion on science of computer.
and I hope this course can help me go even deeper on the subject.

 */