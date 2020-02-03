package com.dennis.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test03 {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("hello", "dennis", "how are you");
        // 遍历一
        strList.forEach(str -> {
            System.out.println(str);
        });

        // 遍历二
        strList.forEach(System.out::println);

        // 赋值一
        ArrayList<String> strings = new ArrayList<>();
        strList.forEach(str -> {
            strings.add(str.toUpperCase());
        });
        System.out.println(strings);

        // 赋值二
        strings.clear();
        strList.stream().map(str -> str.toUpperCase()).forEach(item -> strings.add(item));
        System.out.println(strings);

        // 赋值三
        strings.clear();
        strList.stream().map(String::toUpperCase).forEach(strings::add);
        System.out.println(strings);
    }
}
