package com.dennis.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 描述： mapFlat() demo
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/17 22:07
 */
public class StreamTest04 {

    public static void main(String[] args) {
        // 不采用mapFlat
        List<String> strSrcList = Arrays.asList("today ", "is ", "a ", "happy ", "day ");
        Supplier<Stream<String>> streamSupplier1 = () -> Stream.of("today ", "is ", "a ", "happy ", "day ");
        // list->stream->map(element)->todo...
        strSrcList.stream().map(String::toUpperCase).forEach(System.out::print);
        streamSupplier1.get().map(String::length).forEach(System.out::print);
        System.out.println();

        // 采用mapFlat
        List<String> strList1 = Arrays.asList("today ", "is ");
        List<String> strList2 = Arrays.asList("a ", "wonderful ");
        List<String> strList3 = Arrays.asList("sunny ", "day ");

        Supplier<Stream<List<String>>> streamSupplier2 = () -> Stream.of(strList1, strList2, strList3);
        // Stream<List<T>> -> flatMap(获取到每个Stream中包含的元素) -> map 每个元素做对应的映射操作(获取到一个新的stream) -> option the new stream
        streamSupplier2.get().flatMap(List::stream).map(String::toUpperCase).forEach(System.out::print);
    }

}
