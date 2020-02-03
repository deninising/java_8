package com.dennis.jdk8.supplier;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Supplier;

/**
 * 描述： Supplier demo：不接受参数，返回一个泛型T类的结果对象，可用来代替工厂方法
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/12 23:10
 */
public class SupplierTest01 {
    public static void main(String[] args) {
        Supplier<Student> supplier = () -> new Student();
        Student student = supplier.get();
        System.out.println(student);

        System.out.println("------------------");
        // 方法引用的当时实现lambda表达式
        Supplier<Student> supplier1 = Student::new;
        System.out.println(supplier1.get());

    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class Student {
        private String name = "张三";
        private Integer age = 18;
    }
}

