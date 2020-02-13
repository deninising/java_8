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
 * 描述：描述：自定义收集器深度剖析与并行陷阱
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/9 14:36
 */
public class MySetCollector02<T> implements Collector<T, Set<T>, Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        // 获取中间结果容器
        System.out.println("supplier is invoking ...");
        return () -> {
            System.out.println("新生成一个HashSet<T> 对象......");
            HashSet<T> set = new HashSet<>();
            return set;
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        // 将一个个流元素经过业务逻辑处理后,将处理结果放入到中间结果容器中
        // Set<T>:中间结果容器类型
        System.out.println("accumulator is invoking ...");
        return (container, item) -> {
            container.add(item);
            System.out.println(container); // Characteristics.CONCURRENT特性指定时输出同一个对象,且极易抛出异常:ConcurrentModificationException
            System.out.println(Thread.currentThread().getName());// 当Stream为并行流时,输出不同的线程名,为串行流时,输出结果相同:main
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        // 将多个中间结果容器进行元素合并(只有当Characteristics.CONCURRENT特性指定时该方法才会被调用)
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
    public Set<Collector.Characteristics> characteristics() {
        // 设定收集器特定
        System.out.println("characteristics is invoking ...");
        // 当中间结果容器类型与最终返回结果类型一致时，可以在方法characteristics();中指定IDENTITY_FINISH（同一性）特性，此时JVM底层将不会调用Function<A,R> finish();方法，从而提高一定性能
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH, Collector.Characteristics.UNORDERED, Collector.Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            Supplier<Stream<Integer>> supplier = () -> Stream.of(2, 7, 1, 6, 4, 8, 7, 2, 7, 4, 0, 0, 9, 8, 8, 7, 5, 6, 3, 7, 1, 2, 9);
            // 串行流执行collect()
//        Set<Integer> r = supplier.get().collect(new MySetCollector02<>());// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
            // 并行流执行collect()
            Set<Integer> r = supplier.get().parallel().collect(new MySetCollector02<>());// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
            System.out.println(r);
        }
    }
}
