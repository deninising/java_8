package com.dennis.jdk8.function;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 描述：  较之于Function只能接受一个参数， BiFunction可以接受两个参数，进一步提高了apply()放法实现时的灵活性
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/12 16:28
 */
public class BiFunctionTest01 {
    public static void main(String[] args) {
        List<Person> list =
                Arrays.asList(new Person("张三", 20),
                        new Person("李四", 30),
                        new Person("王五", 50));

        BiFunctionTest01 test01 = new BiFunctionTest01();
        // 按名字条件获取
        List<Person> resultByName = test01.getPersonByName("李四", list);
        System.out.println(resultByName);
        // 按年龄条件获取
        List<Person> resultByAge = test01.getPersonByAge(40, list, (maxAge, personList) ->
                personList.stream().filter(person -> person.getAge() < maxAge).collect(Collectors.toList())
        );
        System.out.println(resultByAge);
    }

    public List<Person> getPersonByName(String name, List<Person> personList) {
        BiFunction<String, List<Person>, List<Person>> biFunction =
                (n, elements) -> elements.stream().filter(person -> person.getName().equals(n)).collect(Collectors.toList());
        return biFunction.apply(name, personList);
    }

    public List<Person> getPersonByAge(Integer maxAge, List<Person> personList, BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(maxAge, personList);
    }

    @Data
    @AllArgsConstructor
    public static class Person {
        private String name;
        private Integer age;
    }
}
