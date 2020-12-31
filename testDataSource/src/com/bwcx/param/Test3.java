package com.bwcx.param;

public class Test3 {

    static int total = 1000;

    public static final String LOCK = "LOCK";

    public static void main(String[] args) {


        Runnable runnable = () -> {
            for (int i = total; i >= 1; i--) {
                synchronized (LOCK) {
                    total--;
                    System.out.println(Thread.currentThread().getName() + "卖了一张票,还剩" + total + "张");
                }
            }

        };
        Thread t1 = new Thread(runnable, "窗口1");
        Thread t2 = new Thread(runnable, "窗口2");
        Thread t3 = new Thread(runnable, "窗口3");
        Thread t4 = new Thread(runnable, "窗口4");
        Thread t5 = new Thread(runnable, "窗口5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
