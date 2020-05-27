package br.com.mhbp.threads.freelock;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class WithoutLockTest {

    private static volatile Map<Integer,Object> hashMap = new HashMap<>();
    private static WithoutLockTest INSTANCE = new WithoutLockTest();

    private Timer timer = new Timer();

    public void start() {

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HashMap<Integer, Object> tempHashMap = new HashMap<>();

                for (int i = 0; i < Integer.MAX_VALUE/10; i++) {

                    tempHashMap.put(i, new Object());   //simulating writing
                }
                hashMap = tempHashMap; // simulating immutability
            }
        }, 100L, 60 * 60 * 1_000L);
    }

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE / 100; i++) {
            Object retrieved = hashMap.get(i); //simulating reading
            if (retrieved == null) {
                // this will happen in genuine case now
                System.out.println(" OMG ! it can happen." + i);
            }
        }
    }
}
