package com.bwcx.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Consumer<T>消费型接口  一个参数,没有返回值
 * Supplier<T>供给型接口  没有参数,有返回值
 * Function<T,R>函数型接口   一个参数,一个返回值
 * Predicate<T>断言型接口    一个参数,返回值是boolean
 *
 * @author wangyuliang
 */
public class LambdaTest {

    public static void main(String[] args) {
        //happy(10000, (m) -> System.out.println("消费" + m + "元"));
        //giveList(10, () -> String.valueOf(new Random().nextInt(100))).forEach(System.out::println);
//        String s = date2Str(new Date(), (x) -> {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            return sdf.format(x);
//        });
//        System.out.println(s);

        System.out.println(isTrue("12334", (x) -> x.length() > 10));
    }

    /**
     * 消费型接口
     *
     * @param money
     * @param consumer
     */
    public static void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 供给型接口
     *
     * @param size
     * @param supplier
     * @return
     */
    public static List<String> giveList(int size, Supplier<String> supplier) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String e = supplier.get();
            list.add(e);
        }
        return list;
    }

    /**
     * 函数式接口
     *
     * @param date
     * @param mF
     * @return
     */
    public static String date2Str(Date date, Function<Date, String> mF) {
        return mF.apply(date);
    }

    public static boolean isTrue(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }
}
