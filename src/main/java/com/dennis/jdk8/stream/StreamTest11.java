package com.dennis.jdk8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

/**
 * 描述： Comparator接口
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/6 21:43
 */
public class StreamTest11 {
    public static void main(String[] args) {
        Supplier<List<String>> supplier = () -> Arrays.asList("hello", "world", "nihao", "welcome");

        // 通用型方法comparing()
        List<String> l1 = supplier.get();
        Collections.sort(l1, (str1, str2) -> str1.length() - str2.length());
        System.out.println(l1);
        System.out.println("=================");

        List<String> l2 = supplier.get();
        Collections.sort(l2, Comparator.comparing(String::length).reversed());
        System.out.println(l2);
        System.out.println("=================");

        // 特定比较方法
        List<String> l3 = supplier.get();
//        Collections.sort(l3, Comparator.comparingInt((str) -> str.length()).reversed()); // 不进行强制性转换无法通过编译
        Collections.sort(l3, Comparator.comparingInt((String str) -> str.length()).reversed()); // 不进行强制性转换能通过编译
        System.out.println(l3);
        System.out.println("=================");

        List<String> l4 = supplier.get();
        Collections.sort(l4, Comparator.comparingInt(String::length).reversed());
        System.out.println(l4);
        System.out.println("=================");

        // 串联比较器
        // 先按照长度进行比较，再按照ASCII码比较（不区分大小写）
        List<String> l5 = supplier.get();
        Collections.sort(l5, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        // Collections.sort(l5,Comparator.comparingInt(String::length).thenComparing((item1,item2)->item1.toLowerCase().compareTo(item2.toLowerCase())));
        System.out.println(l5);
        System.out.println("=================");

        //*****************************************************************************//
        //************************注意:l6排序结果和l7排序结果的区别**************************//
        //*****************************************************************************//

        // 先按照长度进行比较，再按照ASCII码比较（不区分大小写)并进行顺序反转
        List<String> l6 = supplier.get();
        Collections.sort(l6, Comparator.comparingInt(String::length).thenComparing(String::toLowerCase, Comparator.reverseOrder()));
        System.out.println(l6);//[world, nihao, hello, welcome] 可以看到welcome并没有参与顺序反转->why?
        System.out.println("=================");

        // 先按照长度进行比较，再按照ASCII码比较（不区分大小写)，最后全部顺序反转
        List<String> l7 = supplier.get();
        Collections.sort(l7, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER).reversed());
        System.out.println(l7);//[welcome, world, nihao, hello] 可以看到welcome参与了顺序反转->why?
        System.out.println("=================");
    }
}
