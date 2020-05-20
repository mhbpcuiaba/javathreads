package br.com.mhbp.threads.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SimpleDateFormatThreadUnsafetyExample {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static void main(String[] args) {
        String dateStr = "2020-02-20T10:00:00";

        ExecutorService pool = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10000)
                .forEach(i-> pool.submit(() -> parseDate(dateStr)));

        pool.shutdown();
    }

    private static void parseDate(String dateStr) {
        try {
            Date parsedDate = sdf.parse(dateStr);
            System.out.println("Successfully Parsed Date " + parsedDate);
        } catch (ParseException e) {
            System.out.println("Parser Error: " + e.getMessage());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
