package com.bwcx.lambda;

public class Test2_1 {

    public static void main(String[] args) {

        int a = 10;
        Runnable r = () -> System.out.println(a);

        r.run();

        Test2 t = (int c, int b) -> {
            return c + b;
        };
        System.out.println(t.add(1, 2));

        MyFunction t3 = name -> "我是" + name;
        System.out.println(t3.strHandler("张三"));

        Test2 t4 = (f, g) -> f * g;

        System.out.println(t4.add(122, 122));


        System.out.println(strString("\n\taaaaa\n\t", (x) -> x.trim()));

    }

    public static String strString(String str,  MyFunction mF) {
        return mF.strHandler(str);
    }

}
