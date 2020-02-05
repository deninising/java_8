package com.dennis.jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：分组与分区
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/4 17:11
 */
public class StreamTest09 {
    public static void main(String[] args) {
        // 对比SQL语句中的分组查询
        Student s1 = new Student("zhangsan", 100, 20);
        Student s2 = new Student("lisi", 90, 20);
        Student s3 = new Student("wangwu", 90, 30);
        Student s4 = new Student("zhangsan", 80, 40);

        Supplier<Stream<Student>> supplier = () -> Stream.of(s1, s2, s3, s4);

        // SELECT * FROM tb_student GROUP BY name
        // 按照姓名分组
        Map<String, List<Student>> listMapKeyName = supplier.get().collect(Collectors.groupingBy(Student::getName));
        System.out.println(listMapKeyName);
        System.out.println("========================");

        // SELECT * FROM tb_student GROUP BY score
        // 按照分数分组
        Map<Integer, List<Student>> listMapKeyScore = supplier.get().collect(Collectors.groupingBy(Student::getScore));
        System.out.println(listMapKeyScore);
        System.out.println("========================");


        //SELECT name,count(*) FROM tb_student GROUP BY name
        //按照姓名分组并统计个数：
        Map<String, Long> countResult = supplier.get().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(countResult);

        // 分区：只能分两个区，满足指定条件要求的元素一个区，其他所有的元素另一个区
        Map<Boolean, List<Student>> listMap = supplier.get().collect(Collectors.partitioningBy(student -> student.getScore() > 90));
        System.out.println(listMap);
    }

    @Data
    @AllArgsConstructor
    public static class Student {
        private String name;
        private Integer score;
        private Integer age;
    }
}
