package test;

import dao.TickDao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyuliang
 * 乐观锁机制,版本法
 */
public class VersionLock {
    public static void main(String[] args) {
        TickDao tickDao = new TickDao();
        ExecutorService service = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                while (true) {
                    Map<String, Integer> tempTick = tickDao.getRemainTickById(2);
                    if (0 < tempTick.get("total")) {
                        boolean flag = tickDao.sellTick(2, 1, tempTick.get("version"));
                        System.out.println(flag ? Thread.currentThread() + "购票1张成功" : "购票失败");
                    } else {
                        System.out.println("卖完了");
                        //总共用时:116113
                        System.out.println("总共用时:" + (System.currentTimeMillis() - startTime));
                        break;
                    }
                }
            });
        }
        service.shutdown();

    }
}
