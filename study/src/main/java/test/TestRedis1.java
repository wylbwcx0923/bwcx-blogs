package test;

import redis.clients.jedis.Jedis;
import utils.RedisUtil;

public class TestRedis1 {
    public static void main(String[] args) {
        Jedis conn = RedisUtil.getConn();
        conn.setex("flag",20,"TRUE");

        Thread t=new Thread(()->{
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(conn.get("flag"));
            }
        });
        t.start();
    }
}
