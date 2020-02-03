package com.dennis.jdk8.function;

import java.util.function.Function;

/**
 * 描述： Function函数式接口demo(灵活性)
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/12 15:12
 */
public class FunctionTest01 {
    public static void main(String[] args) {
        FunctionTest01 f1 = new FunctionTest01();
        // 加
        Integer r1 = f1.compute(2, x1 -> 8 + x1);
        System.out.println(r1);
        // 减
        Integer r2 = f1.compute(2, x1 -> 8 - x1);
        System.out.println(r2);
        // 乘
        Integer r3 = f1.compute(2, x1 -> x1 * x1);
        System.out.println(r3);
        // 除
        Integer r4 = f1.compute(2, x1 -> 8 / 2);
        System.out.println(r4);

        System.out.println(f1.add(1, 2));
        System.out.println(f1.subtract(1, 2));
        System.out.println(f1.multiply(1, 2));
        System.out.println(f1.divide(1, 2));
    }

    /**
     * 单个自变量的计算方法
     */
    public Integer compute(Integer num, Function<Integer, Integer> function) {
        return function.apply(num);
    }

    /**
     * 传统方式实现计算方法
     */
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public Double divide(Integer a, Integer b) {
        return 1D * a / b;
    }

}
