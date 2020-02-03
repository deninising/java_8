package com.dennis.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：流的短路运算
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/3 22:52
 */
public class StreamTest07 {
    public static void main(String[] args) {
        // 需求：打印列表中长度为5的第一个字符串
        Supplier<Stream<String>> supplier = () -> Stream.of("hello world", "hello", "world", "java hi");

        // 1
        Optional<String> optional = supplier.get().filter(item -> item.length() == 5).findFirst();
        optional.ifPresent(item -> {
            System.out.println(item);
            System.out.println(item.length());
        });

        // 2
        supplier.get().mapToInt(item -> {
            int length = item.length();
            // 问题：此处将会打印哪些字符串？(提示：流的链式调用存在短路运算特性)
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        // 需求：单词的拆分与去重
        Supplier<Stream<String>> supplier1 = () -> Stream.of("hello world", "hello world hello", "hello welcome", "world hello");

        System.out.println("==============================");
        // 错误方法
        List<String[]> collect = supplier1.get().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
        collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        System.out.println("==============================");
        // 正确方法
        Stream<String[]> result = supplier1.get().map(s -> s.split(" "));
        // Stream<String[]> --> Stream<String>
        result.flatMap(strArray -> Stream.of(strArray)).distinct().forEach(System.out::println);
    }
}
