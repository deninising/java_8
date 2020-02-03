package com.dennis.jdk8.stream;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * 描述： generate方法 和 iterate方法
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/17 22:40
 */
public class StreamTest05 {
    public static void main(String[] args) {
        // generate method is suitable for generating constant streams, streams of random elements, etc.
        Stream<String> stream1 = Stream.generate(UUID.randomUUID()::toString);
        stream1.findFirst().ifPresent(System.out::println);

        // iterate 无限迭代中间操作，需结合limit()终止操作一起使用
        Stream.iterate(5, (seed) -> seed * 2).limit(5).forEach(System.out::println);

        // 链式调用:找出大于等于2的元素，然后每个元素乘以2，再舍去前两个元素，又获取前两个并求和
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 7, 9, 11);
        Integer sum = integerStream.filter((integer -> integer >= 2)).mapToInt(item -> item * 2).skip(2).limit(2).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
