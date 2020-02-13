package com.dennis.jdk8.mycollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：一个将流元素转换成Set<T>的收集器
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/8 20:09
 */
public class MySetCollector01<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        // 获取中间结果容器
        System.out.println("supplier is invoking ...");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        // 将一个个流元素经过业务逻辑处理后,将处理结果放入到中间结果容器中
        // Set<T>:中间结果容器类型
        System.out.println("accumulator is invoking ...");
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        // 将多个中间结果容器进行元素合并
        System.out.println("combiner is invoking ...");
        return (before, after) -> {
            before.addAll(after);
            return before;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher is invoking ...");
        // 前一个Set<T>:中间结果容器类型
        // 后一个Set<T>:最返回中结果类型
        //        return Function.identity();// Returns a function that always returns its input argument
        throw new UnsupportedOperationException();// 当中间结果容器类型与最终返回结果类型一致时,推荐如此处理finisher()方法,此外还需在方法characteristics()中指定IDENTITY_FINISH（同一性）特性
    }

    @Override
    public Set<Characteristics> characteristics() {
        // 设定收集器特定
        System.out.println("characteristics is invoking ...");
        // 当中间结果容器类型与最终返回结果类型一致时，可以在方法characteristics();中指定IDENTITY_FINISH（同一性）特性，此时JVM底层将不会调用Function<A,R> finish();方法，从而提高一定性能
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        Supplier<Stream<Integer>> supplier = () -> Stream.of(2, 7, 1, 6, 4, 8, 7, 2, 7, 4, 0, 0, 9, 8, 8, 7, 5, 6, 3, 7, 1, 2, 9);
        List<Integer> r1 = supplier.get().collect(Collectors.toList());// [2, 7, 1, 6, 4, 8, 7, 2, 7, 4, 0, 0, 9, 8, 8, 7, 5, 6, 3, 7, 1, 2, 9]
        System.out.println(r1);

        System.out.println("================");
        Set<Integer> r2 = supplier.get().collect(new MySetCollector01<>());// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(r2);
    }
}