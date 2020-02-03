package com.dennis.jdk8.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述： 员工对象
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/1/14 22:17
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Employee {
    private String name;
    private Integer age;
    private String tag;
}
