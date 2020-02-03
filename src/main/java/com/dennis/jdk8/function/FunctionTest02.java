package com.dennis.jdk8.function;

import java.util.function.Function;

/**
 * 描述： Function函数接口中andThen与compose的对比
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/12 15:34
 */
public class FunctionTest02 {
    public static void main(String[] args) {
        FunctionTest02 test02 = new FunctionTest02();
        Integer r1 = test02.beforeCompute(2, v1 -> v1 * 3, v2 -> v2 * v2);
        System.out.println(r1); // 12
        Integer r2 = test02.afterCompute(2, v1 -> v1 * 3, v2 -> v2 * v2);
        System.out.println(r2); //36
    }

    /**
     * 先执行f2的apply(a)方法，并将其结果作为f1的apply()的参数，且最后执行f1的apply方法
     */
    public Integer beforeCompute(Integer a, Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return f1.compose(f2).apply(a);
    }

    /**
     * 先执行f1的apply(a)方法，并将其结果作为f2的apply()的参数，且最后执行f2的apply方法
     */
    public Integer afterCompute(Integer a, Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return f1.andThen(f2).apply(a);
    }
}
