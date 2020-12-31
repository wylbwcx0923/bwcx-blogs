package test;

import dao.TickDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyuliang
 * 悲观锁
 */
public class TestTick {

    public static void main(String[] args) {
        TickDao tickDao = new TickDao();
        ExecutorService service = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                while (true) {
                    synchronized ("LOCK") {
                        int total = tickDao.getRemainTickById(1).get("total");
                        if (total >= 1) {
                            boolean flag = tickDao.sellTick(1, 1, null);
                            System.out.println(flag ? Thread.currentThread() + "购票1张成功" : "购票失败");
                        } else {
                            System.out.println("卖完了");
                            //总共用时:83629
                            System.out.println("总共用时:" + (System.currentTimeMillis() - startTime));
                            System.exit(0);
                        }
                    }

                }
            });
        }
        service.shutdown();

    }

}
