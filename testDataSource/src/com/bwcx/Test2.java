package com.bwcx;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        String str1 = "sunwukong";
        compare(str1);
    }

    public static void compare(String str1) {
        final char[] chars = str1.toCharArray();
        Arrays.sort(chars);
        System.out.println(Arrays.toString(chars));
        String max1 = Character.toString(chars[chars.length-1]);
        String max2 = Character.toString(chars[chars.length-2]);
        System.out.println(max1+max2);

    }
}
