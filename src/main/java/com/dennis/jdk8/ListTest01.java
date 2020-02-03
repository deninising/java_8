package com.dennis.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ListTest01 {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        for (int i = 0; i < 8; i++) {
            System.out.println(integerList.get(i));
        }
        System.out.println("=================");

        for (Integer i : integerList) {
            System.out.println(i);
        }
        System.out.println("=================");

        integerList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("=================");

        integerList.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("=================");

        // 方发引用的方式创建lambda表达式
        integerList.forEach(System.out::println);
    }
}
