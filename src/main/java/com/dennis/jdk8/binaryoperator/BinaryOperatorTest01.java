package com.dennis.jdk8.binaryoperator;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * 描述： BinaryOperator demo
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/13 21:29
 */
public class BinaryOperatorTest01 {
    public static void main(String[] args) {
        BinaryOperatorTest01 test01 = new BinaryOperatorTest01();
        Integer r1 = test01.compute(2, 3, Integer::sum);
        Integer r2 = test01.compute(2, 3, (v1, v2) -> v1 * v2);
        System.out.println(r1);
        System.out.println(r2);

        String str1 = "Hello";
        String str2 = "hi";
        // 以字符串长度判断大小
        String shortOne = test01.getShortOne(str1, str2, (s1, s2) -> s1.length() - s2.length());
        System.out.println(shortOne);

        // 以首字母的ASCII码值大小判断大小
        String shortOne1 = test01.getShortOne(str1, str2, (s1, s2) -> s1.charAt(0) - s2.charAt(0));
        System.out.println(shortOne1);
    }

    /**
     * 计算两参数为整数的需求
     */
    public Integer compute(Integer a, Integer b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    /**
     * 获取较小值
     */
    public String getShortOne(String s1, String s2, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(s1, s2);
    }
}
