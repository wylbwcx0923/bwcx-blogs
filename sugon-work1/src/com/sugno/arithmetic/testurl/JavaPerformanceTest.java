package com.sugno.arithmetic.testurl;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangyuliang
 */
public class JavaPerformanceTest {
    //超时请求时间
    private static final long TIMEOUT = 3000;
    //超时请求时间单位
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    //默认总共请求数
    private static int totalNums = 100;
    //默认并发数
    private static int concurrentNums = 10;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        try {
            //测试地址
            String url = "https://www.baidu.com/";
            //封装为URL类
            final URL rea = new URL(url);

            //模拟请求
            List<Future<RequestResult>> futureList = simulateRequest(rea);
            //结果展示
            display(futureList);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 模拟请求
     *
     * @param rea
     * @throws InterruptedException
     */
    private static List<Future<RequestResult>> simulateRequest(URL rea)
            throws InterruptedException {

        int seconds = totalNums / concurrentNums;

        ExecutorService service = Executors.newFixedThreadPool(seconds);
        List<Callable<RequestResult>> requestList = new ArrayList<>();
        List<Future<RequestResult>> futureList = new ArrayList<>();
        for (int i = 0; i < seconds; i++) {
            requestList.clear();
            //当前需要请求数
            int curConcurrentNums = (i == seconds - 1)
                    ? totalNums - concurrentNums * i
                    : concurrentNums;

            for (int j = 0; j < curConcurrentNums; j++) {
                requestList.add(() -> req(rea));
            }
            List<Future<RequestResult>> futures = service.invokeAll(requestList, TIMEOUT, UNIT);
            futureList.addAll(futures);
            //间隔一秒
            Thread.sleep(1000);
        }
        service.shutdown();

        return futureList;
    }

    /**
     * 输出结果
     *
     * @param futureList
     */
    private static void display(List<Future<RequestResult>> futureList) {
        List<Long> times = new ArrayList<>();
        int errorNums = 0;
        for (Future<RequestResult> resultSetFutureTask : futureList) {
            try {
                RequestResult requestResult = resultSetFutureTask.get();
                if (requestResult.code == 200) {
                    times.add(requestResult.elapsedTime);
                } else {
                    //作为错误数据统计
                    errorNums++;
                }
            } catch (InterruptedException ex) {
                //作为错误数据统计
                errorNums++;
            } catch (ExecutionException ex) {
                //作为错误数据统计
                errorNums++;
            }
        }
        //输出结果
        times.sort(Long::compareTo);
        System.out.println("平均响应时间：" + times.stream().mapToLong(p -> p.longValue()).sum() / totalNums);
        System.out.println("95% 响应时间：" + times.get(totalNums * 95 / 100));
        System.out.println("最小响应时间：" + times.get(0));
        System.out.println("最大响应时间：" + times.get(totalNums - 1));
        System.out.println("错误率：" + String.format("%.2f", errorNums * 100.00 / totalNums));
    }

    /**
     * 实际完成一次请求
     *
     * @param rea
     * @return
     * @throws IOException
     */
    private static RequestResult req(URL rea) throws IOException {
        long start = System.currentTimeMillis();
        //打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) rea.openConnection();
        //设置请求属性
        connection.setRequestMethod("GET");
        // 设置是否使用缓存
        connection.setUseCaches(false);
        connection.connect();//建立实际的连接
        int code = connection.getResponseCode();
        long end = System.currentTimeMillis();

        return new RequestResult(code, end - start);

    }

    /**
     * 请求结果
     */
    public static class RequestResult {
        int code;
        long elapsedTime;

        public RequestResult(int code, long elapsedTime) {
            this.code = code;
            this.elapsedTime = elapsedTime;
        }
    }
}
