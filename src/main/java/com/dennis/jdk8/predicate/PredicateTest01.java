package com.dennis.jdk8.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 描述： predicate demo
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/12 22:19
 */
public class PredicateTest01 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PredicateTest01 test01 = new PredicateTest01();
        // 大于5
        List<Integer> r1 = test01.numFilter(numbers, num -> num > 5);
        System.out.println(r1);
        System.out.println("++++++++++++++++++++++++");

        // 小于5
        List<Integer> r2 = test01.numFilter(numbers, num -> num <= 5);
        System.out.println(r2);
        System.out.println("++++++++++++++++++++++++");

        // 偶数过滤
        List<Integer> r3 = test01.numFilter(numbers, num -> num % 2 == 0);
        System.out.println(r3);
        System.out.println("++++++++++++++++++++++++");

        // 奇数过滤
        List<Integer> r4 = test01.numFilter(numbers, num -> num % 2 != 0);
        System.out.println(r4);
        System.out.println("++++++++++++++++++++++++");

        // 大于5且为寄数
        List<Integer> r5 = test01.andFilter(numbers, num -> num % 2 != 0, num -> num > 5);
        System.out.println(r5);
        System.out.println("++++++++++++++++++++++++");

        // 反向过滤大于5的数
        List<Integer> r6 = test01.negateFilter(numbers, num -> num > 5);
        System.out.println(r6);
        System.out.println("++++++++++++++++++++++++");

        // 全部显示
        List<Integer> r7 = test01.numFilter(numbers, num -> true);
        System.out.println(r7);
        System.out.println("++++++++++++++++++++++++");

        // 全部不显示
        List<Integer> r8 = test01.numFilter(numbers, num -> false);
        System.out.println(r8);
        System.out.println("++++++++++++++++++++++++");
    }

    public List<Integer> numFilter(List<Integer> numbers, Predicate<Integer> predicate) {
        ArrayList<Integer> integers = new ArrayList<>();
        numbers.forEach(integer -> {
            if (predicate.test(integer)) {
                integers.add(integer);
            }
        });
        return integers;
    }

    public List<Integer> andFilter(List<Integer> numbers, Predicate<Integer> p1, Predicate<Integer> p2) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (Integer integer : numbers) {
            if (p1.and(p2).test(integer)) {
                integers.add(integer);
            }
        }
        return integers;
    }

    public List<Integer> negateFilter(List<Integer> numbers, Predicate<Integer> p1) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (Integer integer : numbers) {
            if (p1.negate().test(integer)) {
                integers.add(integer);
            }
        }
        return integers;
    }
}
