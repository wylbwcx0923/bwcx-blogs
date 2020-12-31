package com.sugno.arithmetic.test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        List<String> f = f(4);
        f.forEach(System.out::println);
    }

    public static List<String> f(int n) {
        List<String> s = new ArrayList<>();
        if (n == 0) {
            s.add("");
        }
        for (int i = 0; i < n; ++i) {
            List<String> l = f(i);
            List<String> r = f(n - i - 1);
            for (String ll : l) {
                for (String rr : r) {
                    s.add("(" + ll + ")" + rr);
                }
            }
        }
        return s;
    }
}
