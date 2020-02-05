package com.dennis.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* 描述：map与flatMap
* @author   Dennis
* @date     2020/2/4 16:39
* @version  1.0
*/
public class StreamTest08 {
    public static void main(String[] args) {
        // 需求：单词的拆分并去重
        Supplier<Stream<String>> supplier = () -> Stream.of("hello world", "hello world hello", "hello welcome", "world hello");

        // 错误方法
        List<String[]> collect = supplier.get().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
        collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        System.out.println("==============================");
        // 正确方法
        Stream<String[]> result = supplier.get().map(s -> s.split(" "));
        // Stream<String[]> --> Stream<String>
        // 调用flatMap后返回的stream中将不在有数组String[],而直接存储最底层数据string为元素类型
        result.flatMap(strArray -> Stream.of(strArray)).distinct().forEach(System.out::println);
    }
}
