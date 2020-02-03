package com.dennis.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 描述：  流的一般创建方式
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/15 23:13
 */
public class StreamTest01 {
    public static void main(String[] args) {
        // 1、通过多个同类元素
        Stream<String> st1 = Stream.of("hello", "java", "world");
        st1.forEach(System.out::println);

        // 2、通过数组
        String[] strArray = new String[]{"today", "is", "a", "happy", "day"};
        Stream<String> st2 = Stream.of(strArray);
        st2.forEach(System.out::println);
        // 1 2本质上都是通过Arrays.stream(T... values)方法实现

        // 通过集合的stream()方法
        List<String> strList = Arrays.asList("tonight", "is", "nice", "night");
        Stream<String> st3 = strList.stream();
        st3.forEach(System.out::println);
    }
}
