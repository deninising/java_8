package com.dennis.jdk8.optional;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 描述： 公司对象
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/14 22:18
 */
@Data
@Accessors(chain = true)
public class Company {
    private String name;
    private List<Employee> employees;
}
