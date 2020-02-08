package com.dennis.jdk8.stream;

import com.dennis.jdk8.stream.StreamTest09.Student;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：Collector辅助类案例，收集器的多级分组与多级分区
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/5 21:18
 */
public class StreamTest10 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 90, 20);
        Student s2 = new Student("lisi", 100, 20);
        Student s3 = new Student("wangwu", 80, 20);
        Student s4 = new Student("zhangsan", 80, 20);
        Student s5 = new Student("zhaoliu", 70, 30);

        Supplier<Stream<Student>> supplier = () -> Stream.of(s1, s2, s3, s4, s5);

        // Collectors.toList()
        System.out.println(supplier.get().collect(Collectors.toList()));
        System.out.println("-------------------");

        // Collectors.counting()
        System.out.println(supplier.get().collect(Collectors.counting()));
        System.out.println(supplier.get().count());
        System.out.println("-------------------");

        // minBy(), maxBy(), averageInt(), summingInt(),  summarizingInt()
        supplier.get().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        supplier.get().collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        System.out.println("average score:" + supplier.get().collect(Collectors.averagingInt(Student::getScore)));
        System.out.println("summing score:" + supplier.get().collect(Collectors.summingInt(Student::getScore)));
        IntSummaryStatistics summaryStatistics = supplier.get().collect(Collectors.summarizingInt(Student::getScore));
        System.out.println(summaryStatistics.toString());
        System.out.println("-------------------");

        // join()
        System.out.println(supplier.get().map(Student::getName).collect(Collectors.joining()));
        System.out.println(supplier.get().map(Student::getName).collect(Collectors.joining(",")));
        System.out.println(supplier.get().map(Student::getName).collect(Collectors.joining(",", "<begin>", "<end>")));

        // 多级分组（体现出collector的结合性）
        // 先安分数分组再按姓名分组
        Map<Integer, Map<String, List<Student>>> map1 = supplier.get()
                .collect(Collectors.groupingBy(Student::getScore, Collectors.groupingBy(Student::getName)));
        System.out.println(map1);
        System.out.println("-------------------");

        // 大于80的分区，其中大于90的又分一区
        Map<Boolean, Map<Boolean, List<Student>>> map2 = supplier.get()
                .collect(Collectors.partitioningBy(s -> s.getScore() >= 80, Collectors.partitioningBy(s -> s.getScore() >= 90)));
        System.out.println(map2);
        System.out.println("-------------------");

        // 大于80分区并统计对应元素个数
        Map<Boolean, Long> map3 = supplier.get().collect(Collectors.partitioningBy(s -> s.getScore() >= 80, Collectors.counting()));
        System.out.println(map3);
        System.out.println("-------------------");

        // 先按名字分组，再找出各组中分数最小元素
        Map<String, Student> map4 = supplier.get()
                .collect(Collectors.groupingBy(Student::getName, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));

        Map<String, Optional<Student>> map5 = supplier.get()
                .collect(Collectors.groupingBy(Student::getName, Collectors.minBy(Comparator.comparingInt(Student::getScore))));
        System.out.println(map4);
        System.out.println(map5);
        System.out.println("-------------------");
    }
}
