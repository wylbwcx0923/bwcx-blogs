package com.bwcx.lambda;

/**
 * @author wangyuliang
 */
public class Test5 {
    /**
     * 这个成员变量的值可以变，但最终必须还是回到原值5
     */
    static int i = 5;

    public static void main(String[] args) {
        assert i == 6;
        System.out.println("如果断言正常，我就被打印");
    }
}
