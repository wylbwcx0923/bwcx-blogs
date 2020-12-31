package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueTest {

    public static BlockingQueue<String> queue = new LinkedBlockingDeque<>();


    public static void main(String[] args) {
        Thread thread = new Thread(new ReadQueue());
        thread.start();
    }

    static class ReadQueue implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                try {
                    final String take = queue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


