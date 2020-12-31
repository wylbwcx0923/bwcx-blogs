package com.bwcx.tick2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ticker {

    public static int total = 100000;

    public static void main(String[] args) {
        ExecutorService e = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            e.submit(() -> {
                while (true) {
                    synchronized ("LOCK") {
                        if (total >= 1) {
                            total--;
                            System.out.println(Thread.currentThread().getName() + "卖了一张票,还剩" + total + "张票");
                        } else {
                            break;
                        }
                    }
                }
            });
        }

        e.shutdown();
    }

}
