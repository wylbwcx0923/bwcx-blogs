package com.bwcx.lambda;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author wangyuliang
 */
public class Test4 {
    public static void main(String[] args) {
        Function<String, Object> handlerStr = (str) -> str.split("-");
        String[] ids = (String[]) handlerStr.apply("1-2-3-4-5-6");
        Arrays.stream(ids).forEach(System.out::println);
    }
}
