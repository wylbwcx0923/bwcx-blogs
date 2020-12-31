package com.bwcx.param;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wangyuliang
 */
public class Test1 {
    public static void main(String[] args) {


        Queue<String> queue=new LinkedList<>();


        Stack<String> stack=new Stack<>();
        stack.push("111");
        stack.push("222");
        stack.push("333");
        stack.push("444");
        stack.push("555");
        stack.push("666");
        stack.push("777");

        System.out.println("aaa"+stack.peek());
        stack.forEach(System.out::println);
    }
}
