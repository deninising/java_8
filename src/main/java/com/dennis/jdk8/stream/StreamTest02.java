package com.dennis.jdk8.stream;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * 描述：
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/15 23:30
 */
public class StreamTest02 {
    public static void main(String[] args) {
        IntStream.of(new int[]{1, 5, 8, 9, 5}).forEach(System.out::println);
        System.out.println("----------------");

        // 左闭右开
        IntStream.range(1, 10).forEach(System.out::println);
        System.out.println("----------------");

        // 全闭
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
        System.out.println("----------------");

        // 需求：1到100求和
        IntStream rangeStream = IntStream.rangeClosed(1, 100);
        int s = rangeStream.reduce(0, (first, second) -> first + second);
        System.out.println(s);
//        int s1 = rangeStream.reduce(0, Integer::sum);
//        System.out.println(s1);

        // 需求：1到100最大值
        int m = rangeStream.reduce(0, (first, second) -> Math.max(first, second));
        System.out.println(m);
//        int m1 = rangeStream.reduce(0, Math::max);
//        System.out.println(m1);

        // 需求： 1到100平均值
        OptionalDouble average = rangeStream.average();
        System.out.println(average);
    }
}
