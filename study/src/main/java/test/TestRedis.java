package test;

import dao.TickDao;
import redis.clients.jedis.Jedis;
import utils.RedisUtil;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRedis {

    public static void main(String[] args) {
        TickDao tickDao = new TickDao();
        long startTime = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                while (true) {
                    Map<String, Integer> tempTick = tickDao.getRemainTickById(2);
                    if (0 < tempTick.get("total")) {
                        Jedis conn = RedisUtil.getConn();
                        if (conn.get("flag") == null) {
                            conn.setex("flag", 20, "TRUE");
                            boolean flag = tickDao.sellTick(2, 1, null);
                            System.out.println(flag ? Thread.currentThread() + "购票1张成功" : "购票失败");
                            conn.del("flag");
                        }
                    } else {
                        System.out.println("卖完了");
                        //总共用时:116113
                        System.out.println("总共用时:" + (System.currentTimeMillis() - startTime));
                        System.exit(0);
                    }
                }
            });

        }
        service.shutdown();
    }

}
