package com.bwcx.slurm;

import java.util.*;

public class Test {

    private static final String RESULT =
            "Account|User\n" +
                    "anxp3|\n" +
                    "anxptest111|\n" +
                    "anxptest112|\n" +
                    "anxptest112|anxptest112\n" +
                    "bgi|\n" +
                    "bgi|BGI\n" +
                    "haowj_5930|\n" +
                    "haowj_5930|haowj\n" +
                    "liyuan01|\n" +
                    "liyuan01|liyuan01\n" +
                    "liyuan02|\n" +
                    "liyuan03|\n" +
                    "liyuan100|\n" +
                    "liyuan100_4997|\n" +
                    "liyuan100_4997|Liyuan100\n" +
                    "root|\n" +
                    "root|root\n" +
                    "user1|\n" +
                    "user1|user1\n" +
                    "wilson|\n" +
                    "wilson|liyuan03\n" +
                    "wilson|anxp3\n";

    public static void main(String[] args) {
        String[] rows = RESULT.split("\n");
        Map<String, String> map = new HashMap<>();
        //获取Map中所有的key
        Set<String> keys = map.keySet();
        for (int i = 1; i < rows.length; i++) {
            //以\n拆分,那么每一行就是一个数组
            String[] accountAry = rows[i].split("\\|");
            if (accountAry.length > 1) {
                //如果这个Key已经存在,说明这个账号已经加入了
                if (keys.contains(accountAry[0])) {
                    //使用,拼接它下面的用户
                    map.put(accountAry[0], map.get(accountAry[0]) + "," + accountAry[1]);
                } else {
                    //不存在就加进去
                    map.put(accountAry[0], accountAry[1]);
                }
            } else {
                //相同的账号,Map会自动覆盖掉,达到去重的效果
                map.put(accountAry[0], "");
            }
        }
        List<SlurmAccount> list = new ArrayList<>();
        keys.forEach(key -> {
            SlurmAccount account = new SlurmAccount();
            account.setAccount(key);
            account.setUsers(map.get(key).replaceFirst(",", ""));
            list.add(account);
        });

        list.forEach(System.out::println);
    }


}
