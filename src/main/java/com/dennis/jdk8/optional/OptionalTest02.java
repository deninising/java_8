package com.dennis.jdk8.optional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 描述： 标准函数式接口调用风格实例
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/14 22:20
 */
public class OptionalTest02 {
    public static void main(String[] args) {
        Company company = new Company();
        Employee em1 = new Employee("张三", 30, null);
        Employee em2 = new Employee("李四", 40, null);
        Employee em3 = new Employee("王五", 50, null);

        List<Employee> employees = Arrays.asList(em1, em2, em3);
        company.setName("guotie").setEmployees(employees);

        List<Employee> emList = company.getEmployees();

        Optional<List<Employee>> opt = Optional.ofNullable(emList);
        List<Employee> result = opt.map(ems -> {
            ems.forEach(employee -> {
                if (employee.getAge() >= 40) {
                    employee.setTag("老员工");
                }
            });
            return ems;
        }).orElse(Collections.emptyList());
        System.out.println(result);
    }
}
