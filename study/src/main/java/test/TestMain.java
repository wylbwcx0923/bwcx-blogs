package test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }


        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            synchronized ("LOCK") {
                service.submit(() ->
                        list.forEach(item -> System.out.println(Thread.currentThread().getName() + "输出:" + item))
                );
            }
        }

        service.shutdownNow();
    }
}
