package com.dennis.jdk8.optional;

import java.util.Optional;

/**
 * 描述： optional demo
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/14 21:37
 */
public class OptionalTest01 {
    public static void main(String[] args) {

        // Object obj = Person.getObj();
        // Optional<Object> opt = Optional.of(obj)    能确定obj一定不为空
        // Optional<Object> opt = Optional.empty()    所得到的opt包含一个空元素
        // Optional<Object> opt = Optional.ofNullable(obj)  不确定obj是否为空

        Optional<String> opt = Optional.of("hello");

        // 不推荐的使用方法
        if (opt.isPresent()) {
            System.out.println(opt.get());
        }
        System.out.println("----------------");

        // 推荐使用的方式

        // 1、optional 所包含的元素不为null则执行函数式接口方法，否则执行（不为空）
        opt.ifPresent(item-> System.out.println(item.toUpperCase()));
        System.out.println(opt.get());
        System.out.println("----------------");
        // 2、optional 所包含的元素不为null则执行函数式接口方法，否则执行（为空）
        Optional<Object> opt1 = Optional.empty();
        opt1.ifPresent(System.out::println);
        System.out.println("----------------");
        // 3、optional 所包含的元素不为null则应用所包含元素，否则应用其他元素（不为空）
        System.out.println(opt.orElse("world"));
        System.out.println("----------------");
        // 4、optional 所包含的元素不为null则应用所包含元素，否则应用其他元素（为空）
        System.out.println(opt1.orElse("world"));
        System.out.println("----------------");
        // 5、optional 所包含的元素不为null则应用所包含元素，否则应用其他元素（不为空）
        System.out.println(opt.orElseGet(()->"Hi"));
        System.out.println("----------------");
        // 6、optional 所包含的元素不为null则应用所包含元素，否则应用其他元素（为空）
        System.out.println(opt1.orElseGet(()->"Hi"));
        System.out.println("----------------");
    }
}
