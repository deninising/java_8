<center>
    <h1>
        JAVA8新特性
    </h1>
</center>

## lambda表达式与函数式接口

- **定义**

  ![1578225721716](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578225721716.png)

- **必要性**

  ![1578225753125](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578225753125.png)

- **javascript回调方法实例**

  ```javascript
  // javascript 中的函数作为参数
  a.execute(callback(event){
            //do next
            });
  ```

- **Java匿名内部类实例**

  ![1578226018767](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578226018767.png)

- **基本表达式**

  ```java
  a.function(
  (parm1,parm2,parm3)->{
      //do next
  });
  ```

- **函数式接口**（functionInterface）

  **定义：**有且只有一个抽象方法的接口称为函数式接口（Conceptually, a functional interface has exactly one abstract method.）

  **实例化方式：**lambda表达式、方法引用、构造器引用（Note that instances of functional interfaces can be created with lambda expressions, method references, or constructor references）

- **函数式接口注解（@FunctionalInterface）：**
  - 满足函数式接口定义的接口可以显示的在该接口上加上@FunctionalInterface注解，表示这是一个函数式接口
  - 任何不满足函数式接口的接口若加上@FunctionalInterface，则编译器会报错
  - 任何满足函数式接口定义的接口但没有添加@FunctionalInterface注解，编译器同样会按照函数式接口编译该接口

- **lambda表达式的作用：**

  ![1578233716149](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578233716149.png)



## 函数式接口与方法引用

- **方法引用实例：**

  ```java
  package com.dennis.jdk8;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Consumer;
  
  public class ListTest01 {
      public static void main(String[] args) {
          List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
          for (int i = 0; i < 8; i++) {
              System.out.println(integerList.get(i));
          }
          System.out.println("=================");
  
          for (Integer i : integerList) {
              System.out.println(i);
          }
          System.out.println("=================");
  
          integerList.forEach(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) {
                  System.out.println(integer);
              }
          });
          System.out.println("=================");
  
          integerList.forEach(integer -> {
              System.out.println(integer);
          });
          System.out.println("=================");
  
          // 方发引用的方式创建lambda表达式
          integerList.forEach(System.out::println);
      }
  }
  
  ```

- **lambda表达式的本质：**（可以通过执行结果发现，lambda表达式本质上是一个实现了函数式接口的对象）

  ```java
  package com.dennis.jdk8;
  
  /**
   * 一个.java文件中只能有一个public类
   */
  @FunctionalInterface
  interface MyFuncInterface {
      void test01();
      @Override
      public String toString();
  }
  
  public class Test02 {
      public void test(MyFuncInterface myFuncInterface) {
          System.out.println("调用函数式接口中抽象方法前");
          myFuncInterface.test01();
          System.out.println("调用函数式接口中抽象方法后");
      }
  
      public static void main(String[] args) {
          Test02 test02 = new Test02();
  
          // 传统匿名内部类的方式调用函数式接口中的方法
          test02.test(new MyFuncInterface() {
              @Override
              public void test01() {
                  System.out.println("匿名内部类方式调用:实现并成功调用函数是接口中的抽象方法");
              }
          });
          System.out.println("===============================");
  
          // lambda方式调用
          test02.test(() -> {
              System.out.println("lambda方式调用:实现并成功调用函数是接口中的抽象方法");
          });
          System.out.println("===============================");
  		// lambda表达式的本质
          MyFuncInterface myFuncInterface = () -> {
              System.out.println("实现并成功调用函数是接口中的抽象方法");
          };
          test02.test(myFuncInterface);
          System.out.println("===============================");
          // myFuncInterface对象的本质
          System.out.println("myFuncInterface对象的本质");
          System.out.println(myFuncInterface.getClass());
          System.out.println(myFuncInterface.getClass().getSuperclass());
          System.out.println(myFuncInterface.getClass().getInterfaces().length);
          System.out.println(myFuncInterface.getClass().getInterfaces()[0]);
      }
  }
  
  ```

  **输出结果：**

  ```
  调用函数式接口中抽象方法前
  匿名内部类方式调用:实现并成功调用函数是接口中的抽象方法
  调用函数式接口中抽象方法后
  ===============================
  调用函数式接口中抽象方法前
  lambda方式调用:实现并成功调用函数是接口中的抽象方法
  调用函数式接口中抽象方法后
  ===============================
  调用函数式接口中抽象方法前
  实现并成功调用函数是接口中的抽象方法
  调用函数式接口中抽象方法后
  ===============================
  myFuncInterface对象的本质
  class com.dennis.jdk8.Test02$$Lambda$2/1078694789
  class java.lang.Object
  1
  interface com.dennis.jdk8.MyFuncInterface
  
  Process finished with exit code 0
  ```

  **基本语法：**![1578820600131](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820600131.png)![1578820635738](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820635738.png)![1578820662320](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820662320.png)![1578820730262](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820730262.png)![1578820852272](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820852272.png)![1578820910511](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578820910511.png)

## Function函数式接口与流初步

- **Function灵活性**：通过apply（）方法将行为作为"参数"传入

  ```java
  package com.dennis.jdk8.function;
  
  import java.util.function.Function;
  
  /**
   * 描述： Function函数式接口demo(灵活性体现)
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/12 15:12
   */
  public class FunctionTest01 {
      public static void main(String[] args) {
          FunctionTest01 f1 = new FunctionTest01();
          // 加
          Integer r1 = f1.compute(2, x1 -> 8 + x1);
          System.out.println(r1);
          // 减
          Integer r2 = f1.compute(2, x1 -> 8 - x1);
          System.out.println(r2);
          // 乘
          Integer r3 = f1.compute(2, x1 -> x1 * x1);
          System.out.println(r3);
          // 除
          Integer r4 = f1.compute(2, x1 -> 8 / 2);
          System.out.println(r4);
  
          System.out.println(f1.add(1, 2));
          System.out.println(f1.subtract(1, 2));
          System.out.println(f1.multiply(1, 2));
          System.out.println(f1.divide(1, 2));
      }
  
      /**
       * 单个自变量的计算方法
       */
      public Integer compute(Integer num, Function<Integer, Integer> function) {
          return function.apply(num);
      }
  
      /**
       * 传统方式实现计算方法
       */
      public Integer add(Integer a, Integer b) {
          return a + b;
      }
  
      public Integer subtract(Integer a, Integer b) {
          return a - b;
      }
  
      public Integer multiply(Integer a, Integer b) {
          return a * b;
      }
  
      public Double divide(Integer a, Integer b) {
          return 1D * a / b;
      }
  
  }
  
  ```

- **andThen与compose方法的对比**

  - andThen：先执行调用者的apply()方法，再执行作为参数的Founction对象的apply方法
  - compose:  与andThen执行apply的顺序相反

  ```java
  package com.dennis.jdk8.function;
  
  import java.util.function.Function;
  
  /**
   * 描述： Function函数接口中andThen与compose的对比
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/12 15:34
   */
  public class FunctionTest02 {
      public static void main(String[] args) {
          FunctionTest02 test02 = new FunctionTest02();
          Integer r1 = test02.beforeCompute(2, v1 -> v1 * 3, v2 -> v2 * v2);
          System.out.println(r1); // 12
          Integer r2 = test02.afterCompute(2, v1 -> v1 * 3, v2 -> v2 * v2);
          System.out.println(r2); //36
      }
  
      /**
       * 先执行f2的apply(a)方法，并将其结果作为f1的apply()的参数，且最后执行f1的apply方法
       */
      public Integer beforeCompute(Integer a, Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
          return f1.compose(f2).apply(a);
      }
  
      /**
       * 先执行f1的apply(a)方法，并将其结果作为f2的apply()的参数，且最后执行f2的apply方法
       */
      public Integer afterCompute(Integer a, Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
          return f1.andThen(f2).apply(a);
      }
  }
  
  ```

- **BiFunction:较之于Function只能接受一个参数， BiFunction可以接受两个参数，进一步提高了apply()方法实现时的灵活性**

  注意：lambda表达式的语法

  ```java
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
  ```

## Predicate函数式接口

- **作用：提供判断方法test(),且提供或（or）、与(and)、非(nagete)逻辑计算**

  ```java
  package com.dennis.jdk8.predicate;
  
  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Predicate;
  
  /**
   * 描述： predicate demo
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/12 22:19
   */
  public class PredicateTest01 {
      public static void main(String[] args) {
          List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
          PredicateTest01 test01 = new PredicateTest01();
          // 大于5
          List<Integer> r1 = test01.numFilter(numbers, num -> num > 5);
          System.out.println(r1);
          System.out.println("++++++++++++++++++++++++");
  
          // 小于5
          List<Integer> r2 = test01.numFilter(numbers, num -> num <= 5);
          System.out.println(r2);
          System.out.println("++++++++++++++++++++++++");
  
          // 偶数过滤
          List<Integer> r3 = test01.numFilter(numbers, num -> num % 2 == 0);
          System.out.println(r3);
          System.out.println("++++++++++++++++++++++++");
  
          // 奇数过滤
          List<Integer> r4 = test01.numFilter(numbers, num -> num % 2 != 0);
          System.out.println(r4);
          System.out.println("++++++++++++++++++++++++");
  
          // 大于5且为寄数
          List<Integer> r5 = test01.andFilter(numbers, num -> num % 2 != 0, num -> num > 5);
          System.out.println(r5);
          System.out.println("++++++++++++++++++++++++");
  
          // 反向过滤大于5的数
          List<Integer> r6 = test01.negateFilter(numbers, num -> num > 5);
          System.out.println(r6);
          System.out.println("++++++++++++++++++++++++");
  
          // 全部显示
          List<Integer> r7 = test01.numFilter(numbers, num -> true);
          System.out.println(r7);
          System.out.println("++++++++++++++++++++++++");
  
          // 全部不显示
          List<Integer> r8 = test01.numFilter(numbers, num -> false);
          System.out.println(r8);
          System.out.println("++++++++++++++++++++++++");
      }
  	
     	// 单个判断
      public List<Integer> numFilter(List<Integer> numbers, Predicate<Integer> predicate) {
          ArrayList<Integer> integers = new ArrayList<>();
          numbers.forEach(integer -> {
              if (predicate.test(integer)) {
                  integers.add(integer);
              }
          });
          return integers;
      }
  
      // 与组合判断
      public List<Integer> andFilter(List<Integer> numbers, Predicate<Integer> p1, Predicate<Integer> p2) {
          ArrayList<Integer> integers = new ArrayList<>();
          for (Integer integer : numbers) {
              if (p1.and(p2).test(integer)) {
                  integers.add(integer);
              }
          }
          return integers;
      }
  	
      // 逻辑非判断
      public List<Integer> negateFilter(List<Integer> numbers, Predicate<Integer> p1) {
          ArrayList<Integer> integers = new ArrayList<>();
          for (Integer integer : numbers) {
              if (p1.negate().test(integer)) {
                  integers.add(integer);
              }
          }
          return integers;
      }
  }
  ```

## Supplier函数式接口

- **get()方法不接受参数，返回一个泛型T类的结果对象，可用来代替工厂方法**

  ```java
  package com.dennis.jdk8.supplier;
  
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import lombok.experimental.Accessors;
  
  import javax.swing.plaf.SplitPaneUI;
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
  ```

## BinaryOperator函数式接口

- **BinaryOperator继承至BiFunction函数式接口，是一个参数类型和返回值类型都相同的特殊BiFunction接口**

  ![1578843495732](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1578843495732.png)

  ```java
  package com.dennis.jdk8.binaryoperator;
  
  import java.util.Comparator;
  import java.util.function.BinaryOperator;
  
  /**
   * 描述： BinaryOperator demo
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/13 21:29
   */
  public class BinaryOperatorTest01 {
      public static void main(String[] args) {
          BinaryOperatorTest01 test01 = new BinaryOperatorTest01();
          Integer r1 = test01.compute(2, 3, Integer::sum);
          Integer r2 = test01.compute(2, 3, (v1, v2) -> v1 * v2);
          System.out.println(r1);
          System.out.println(r2);
  
          String str1 = "Hello";
          String str2 = "hi";
          // 以字符串长度判断大小
          String shortOne = test01.getShortOne(str1, str2, (s1, s2) -> s1.length() - s2.length());
          System.out.println(shortOne);
  
          // 以首字母的ASCII码值大小判断大小
          String shortOne1 = test01.getShortOne(str1, str2, (s1, s2) -> s1.charAt(0) - s2.charAt(0));
          System.out.println(shortOne1);
      }
  
      /**
       * 计算两参数为整数的需求
       */
      public Integer compute(Integer a, Integer b, BinaryOperator<Integer> binaryOperator) {
          return binaryOperator.apply(a, b);
      }
  
      /**
       * 获取较小值
       */
      public String getShortOne(String s1, String s2, Comparator<String> comparator) {
          return BinaryOperator.minBy(comparator).apply(s1, s2);
      }
  }
  ```

## optional深入详解

- **用于解决对空值的判断，避免出现NullPointerException**

  **传统方式：**

  ```java
  // 传统方式判断空,比较繁琐
  if (null != person){
      Address address = person.getAddress();
      if(null != address){
          // todo others
      }
  }
  ```

  **optional不推荐的使用方式：与传统方式在形式上没有任何区别**

  ```java
  // 以下为不推荐方式
  String s = null;
  s = Person.getName;
  if (null != s){
      // todo sth
  }
  
  Option<String> option = Option.of(s);
  if(option.isPresent){
      // todo sth
      Object obj = option.get();
      // todo others
  }
  ```

  **optional的推荐使用方式：采用函数式接口调用**

  ```java
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
  
          // 1、optional 所包含的元素不为null则执行函数式接口方法，否则不执行（不为空）
          opt.ifPresent(item-> System.out.println(item.toUpperCase()));
          System.out.println(opt.get());
          System.out.println("----------------");
          // 2、optional 所包含的元素不为null则执行函数式接口方法，否则不执行（为空）
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
  ```

  **标准的函数式风格调用试例：**

  ```java
  
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
          // 标记老员工
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
  ```

## 方法引用实现函数式接口

![1579016060897](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1579016060897.png)

## stream 流

- **流的基本概念和介绍：一个支持串行和并行聚合操作的元素序列**

  ![1579097161474](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1579097161474.png)![image-20200203212019041](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\image-20200203212019041.png)![image-20200203212401309](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\image-20200203212401309.png)

- **流的构成：分成三部分**

  1. 源（数据）

  2. 零个或多个中间操作（惰性求值）

  3. 终止操作（及早求值）

     ​	To perform a computation, stream operations are composed into a stream pipeline .A stream pipeline consists of a **source** (which might be an array, a collection, a generator function, an I/O channel, etc), **zero or more ntermediate operations** (which transform a stream into another stream, such as Stream#filter(Predicate) and **a terminal operation** (which produces a result or side-effect, such as Stream#count() or Stream#forEach(Consumer)). 

     ​	Streams are lazy; computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.

- **流的一般创建方式**

  ```java 
  package com.dennis.jdk8.stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.stream.Stream;
  
  /**
   * 描述：  流的一般创建方式
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/15 23:13
   */
  public class StreamTest01 {
      public static void main(String[] args) {
          // 1、通过多个同类元素
          Stream<String> st1 = Stream.of("hello", "java", "world");
  
          // 2、通过数组
          String[] strArray = new String[]{"today", "is", "a", "happy", "day"};
          Stream<String> st2 = Stream.of(strArray);
          // 1 2本质上都是通过Arrays.stream(T... values)方法实现
  
          // 通过集合的stream()方法
          List<String> strList = Arrays.asList("tonight", "is", "nice", "duration");
          Stream<String> st3 = strList.stream();
      }
  }
  ```

- **流带来的简化**

  ```java 
  package com.dennis.jdk8.stream;
  
  import java.util.OptionalDouble;
  import java.util.stream.IntStream;
  
  /**
   * 描述：
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/15 23:30
   */
  public class StreamTest02 {
      public static void main(String[] args) {
          IntStream.of(new int[]{1, 5, 8, 9, 5}).forEach(System.out::println);
          System.out.println("----------------");
  
          // 左闭右开
          IntStream.range(1, 10).forEach(System.out::println);
          System.out.println("----------------");
  
          // 全闭
          IntStream.rangeClosed(1, 10).forEach(System.out::println);
          System.out.println("----------------");
  
          // 需求：1到100求和
          IntStream rangeStream = IntStream.rangeClosed(1, 100);
          int s = rangeStream.reduce(0, (first, second) -> first + second);
          System.out.println(s);
  //        int s1 = rangeStream.reduce(0, Integer::sum);
  //        System.out.println(s1);
  
          // 需求：1到100最大值
          int m = rangeStream.reduce(0, (first, second) -> Math.max(first, second));
          System.out.println(m);
  //        int m1 = rangeStream.reduce(0, Math::max);
  //        System.out.println(m1);
  
          // 需求： 1到100平均值
          OptionalDouble average = rangeStream.average();
          System.out.println(average);
      }
  }
  
  ```

- **流的进一步应用：list，array 与 stream 相互转换**

  ```java
  package com.dennis.jdk8.stream;
  
  import java.util.*;
  import java.util.function.Supplier;
  import java.util.stream.Collectors;
  import java.util.stream.Stream;
  
  /**
   * 描述： list，array 与 stream 相互转换
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/16 20:43
   */
  public class StreamTest03 {
      public static void main(String[] args) throws InterruptedException {
          // elements --> stream
          Supplier<Stream<String>> streamSupplier = () -> Stream.of("today", "is", "a", "nice", "day");
  
          // stream --> array
          String[] strArray1 = streamSupplier.get().toArray(String[]::new);
          System.out.println(Arrays.toString(strArray1));
  
          // stream --> list
          List<String> strList1 = streamSupplier.get().collect(Collectors.toList());
          strList1.forEach(System.out::println);
  
          LinkedList<String> strList2 = streamSupplier.get().collect(
                  () -> new LinkedList<>(),
                  (strList, item) -> strList.add(item),
                  (resultList, strList) -> resultList.addAll(strList)
          );
          strList2.forEach(System.out::println);
  
          ArrayList<Object> strList3 = streamSupplier.get().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
          strList3.forEach(System.out::println);
  
          // Stream -> set
          TreeSet<String> treeSet = streamSupplier.get().collect(Collectors.toCollection(TreeSet::new));
          treeSet.forEach(System.out::println);
  
          // join
          String strJoin = streamSupplier.get().collect(Collectors.joining());
          System.out.println(strJoin);
      }
  }
  ```

- **使用stream注意事项：Java – Stream has already been operated upon or closed**

  ```java
  package com.mkyong.java8;
  
  import java.util.Arrays;
  import java.util.stream.Stream;
  
  public class TestJava8 {
  
  public static void main(String[] args) {
  
      String[] array = {"a", "b", "c", "d", "e"};
      Stream<String> stream = Arrays.stream(array);
  
      // loop a stream
      stream.forEach(x -> System.out.println(x));
  
      // reuse it to filter again! throws IllegalStateException
      long count = stream.filter(x -> "b".equals(x)).count();
      System.out.println(count);
      }
  }
  ```

  **Output：**

  java.lang.IllegalStateException: stream has already been operated upon or closed

- **正确用法:example—reuse a stream correctly**

  ```java
  package com.dennis.jdk8.stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Supplier;
  import java.util.stream.Stream;
  
  /**
   * 描述： mapFlat() demo
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/17 22:07
   */
  public class StreamTest04 {
  
      public static void main(String[] args) {
          // 不采用mapFlat
          List<String> strSrcList = Arrays.asList("today ", "is ", "a ", "happy ", "day ");
          Supplier<Stream<String>> streamSupplier1 = () -> Stream.of("today ", "is ", "a ", "happy ", "day ");
          // list->stream->map(element)->todo...
          strSrcList.stream().map(String::toUpperCase).forEach(System.out::print);
          streamSupplier1.get().map(String::length).forEach(System.out::print);
          System.out.println();
  
          // 采用mapFlat
          List<String> strList1 = Arrays.asList("today ", "is ");
          List<String> strList2 = Arrays.asList("a ", "wonderful ");
          List<String> strList3 = Arrays.asList("sunny ", "day ");
  
          Supplier<Stream<List<String>>> streamSupplier2 = () -> Stream.of(strList1, strList2, strList3);
          // Stream<List<T>> -> flatMap(获取到每个Stream中包含的元素) -> map 每个元素做对应的映射操作(获取到一个新的stream) -> option the new stream
          streamSupplier2.get().flatMap(List::stream).map(String::toUpperCase).forEach(System.out::print);
      }
  
  }
  ```

- **generate()和Iterate()**

  ```java
  package com.dennis.jdk8.stream;
  
  import java.util.UUID;
  import java.util.stream.Stream;
  
  /**
   * 描述： generate方法 和 iterate方法
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/1/17 22:40
   */
  public class StreamTest05 {
      public static void main(String[] args) {
          // generate method is suitable for generating constant streams, streams of random elements, etc.
          Stream<String> stream1 = Stream.generate(UUID.randomUUID()::toString);
          stream1.findFirst().ifPresent(System.out::println);
  
          // iterate 无限迭代中间操作，其后需紧跟limit()终止操作一起使用
          Stream.iterate(5, (seed) -> seed * 2).limit(5).forEach(System.out::println);
  
          // 链式调用:找出大于等于2的元素，然后每个元素乘以2，再舍去前两个元素，又获取前两个并求和
          Stream<Integer> integerStream = Stream.of(1, 3, 5, 7, 9, 11);
          Integer sum = integerStream.filter((integer -> integer >= 2)).map(item -> item * 2).skip(2).limit(2).reduce(0, Integer::sum);
          System.out.println(sum);
      }
  }
  
  ```

- **内部迭代与外部迭代**

  ![](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1579357345941.png)

  **外内部迭代示意图**

  ![](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\1579357796154.png)

- **Stream的并行与短路运算**

  1. 串行流即：单线程执行
  2. 并行流即：多线程执行（调用parrelStream()方法）

  ```Java
  package com.dennis.jdk8.stream;
  
  import java.util.ArrayList;
  import java.util.List;
  import java.util.UUID;
  import java.util.concurrent.TimeUnit;
  
  /**
   * 描述：并发流parallelStream()与串行流stream()
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/2/3 22:30
   */
  public class StreamTest06 {
      public static void main(String[] args) {
          List<String> list = new ArrayList<>(5000000);
  
          for (int i = 0; i < 5000000; i++) {
              list.add(UUID.randomUUID().toString());
          }
  
          // 排序
          System.out.println("开始排序");
          long startTime = System.nanoTime();
  
          // 串行流耗时：3820毫秒（单个线程执行）
  //        list.stream().sorted().findFirst();
  
          // 并行流耗时：1324毫秒（多个线程执行）
          list.parallelStream().sorted().findFirst();
          long endTime = System.nanoTime();
  
          long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
          System.out.println("耗时：" + duration + "毫秒");
      }
  }
  
  ```

  3. 短路运算即：当前运算只要不满足条件，后续的运算将不再执行(与逻辑运算中强于&& ，强或|| 的原理类似)

  ```java
  package com.dennis.jdk8.stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.Optional;
  import java.util.function.Supplier;
  import java.util.stream.Collectors;
  import java.util.stream.Stream;
  
  /**
   * 描述：流的短路运算
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/2/3 22:52
   */
  public class StreamTest07 {
      public static void main(String[] args) {
          // 需求：打印列表中长度为5的第一个字符串
          Supplier<Stream<String>> supplier = () -> Stream.of("hello world", "hello", "world", "java hi");
  
          // 1
          Optional<String> optional = supplier.get().filter(item -> item.length() == 5).findFirst();
          optional.ifPresent(item -> {
              System.out.println(item);
              System.out.println(item.length());
          });
  
          // 2
          supplier.get().mapToInt(item -> {
              int length = item.length();
              // 问题：此处将会打印哪些字符串？(提示：流的链式调用存在短路运算特性)
              System.out.println(item);
              return length;
          }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
          
          
          // 需求：单词的拆分与去重
          Supplier<Stream<String>> supplier1 = () -> Stream.of("hello world", "hello world hello", "hello welcome", "world hello");
  
          System.out.println("==============================");
          // 错误方法
          List<String[]> collect = supplier1.get().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
          collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));
  
          System.out.println("==============================");
          // 正确方法
          Stream<String[]> result = supplier1.get().map(s -> s.split(" "));
          // Stream<String[]> --> Stream<String>
          result.flatMap(strArray -> Stream.of(strArray)).distinct().forEach(System.out::println);
      }
  }
  
  ```

-  **map 与 flatmap的区别:**把Stream中 的每一个元素，映射成另外一个元素。

  1. map生成的是个1:1映射，每个输入元素，都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要 flatMap。

  2. map和flatMap的方法声明是不一样的
     <R> Stream<R>      map(Function<? super T, ? extends R> mapper);
     <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

  3. ```java
     // stream1中的每个元素都是一个List集合对象
     Stream<List<Integer>> stream1 = Stream.of(
     				 Arrays.asList(1),
     				 Arrays.asList(2, 3),
     				 Arrays.asList(4, 5, 6)
     			 );
     			Stream<Integer> stream2 = stream1.
     			flatMap((e) -> e.stream());
     			
     stream2.forEach(e->System.out.println(e));//输出1 2 3 4 5 6
     // flatMap 把 stream1 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终新的 stream2 里面已经没有 List 了，都是直接的数字。
     
     例子:
     Stream<String> stream1 = Stream.of("tom.Li","lucy.Liu");
     // flatMap方法把stream1中的每一个字符串都用[.]分割成了俩个字符串
     // 最后返回了一个包含4个字符串的stream2
     Stream<String> stream2 = stream1.flatMap(s->Stream.of(s.split("[.]")));
     stream2.forEach(System.out::println);//输出	tom Li lucy Liu
     ```

  **例子：**

  ```java
  package com.dennis.jdk8.stream;
  
  import java.util.Arrays;
  import java.util.List;
  import java.util.function.Supplier;
  import java.util.stream.Collectors;
  import java.util.stream.Stream;
  
  /**
  * 描述：map与flatMap
  * @author   Dennis
  * @date     2020/2/4 16:39
  * @version  1.0
  */
  public class StreamTest08 {
      public static void main(String[] args) {
          // 需求：单词的拆分并去重
          Supplier<Stream<String>> supplier = () -> Stream.of("hello world", "hello world hello", "hello welcome", "world hello");
  
          // 错误方法
          List<String[]> collect = supplier.get().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
          collect.forEach(item -> Arrays.asList(item).forEach(System.out::println));
  
          System.out.println("==============================");
          // 正确方法
          Stream<String[]> result = supplier.get().map(s -> s.split(" "));
          // Stream<String[]> --> Stream<String>
          // 调用flatMap后返回的stream中将不在有数组String[],而直接存储最底层数据string为元素类型
          result.flatMap(strArray -> Stream.of(strArray)).distinct().forEach(System.out::println);
      }
  }
  ```

- **分组/分区**

  ```java
  package com.dennis.jdk8.stream;
  
  import lombok.AllArgsConstructor;
  import lombok.Data;
  
  import java.util.List;
  import java.util.Map;
  import java.util.function.Supplier;
  import java.util.stream.Collectors;
  import java.util.stream.Stream;
  
  /**
   * 描述：分组与分区
   *
   * @author Dennis
   * @version 1.0
   * @date 2020/2/4 17:11
   */
  public class StreamTest09 {
      public static void main(String[] args) {
          // 对比SQL语句中的分组查询
          Student s1 = new Student("zhangsan", 100, 20);
          Student s2 = new Student("lisi", 90, 20);
          Student s3 = new Student("wangwu", 90, 30);
          Student s4 = new Student("zhangsan", 80, 40);
  
          Supplier<Stream<Student>> supplier = () -> Stream.of(s1, s2, s3, s4);
          
          // SELECT * FROM tb_student GROUP BY name
          // 按照姓名分组
          Map<String, List<Student>> listMapKeyName = supplier.get().collect(Collectors.groupingBy(Student::getName));
          System.out.println(listMapKeyName);
          System.out.println("========================");
          
          // SELECT * FROM tb_student GROUP BY score
          // 按照分数分组
          Map<Integer, List<Student>> listMapKeyScore = supplier.get().collect(Collectors.groupingBy(Student::getScore));
          System.out.println(listMapKeyScore);
          System.out.println("========================");
  
  
          //SELECT name,count(*) FROM tb_student GROUP BY name
          //按照姓名分组并统计个数：
          Map<String, Long> countResult = supplier.get().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
          System.out.println(countResult);
  
          // 分区：只能分两个区，满足指定条件要求的元素一个区，其他所有的元素另一个区
          Map<Boolean, List<Student>> listMap = supplier.get().collect(Collectors.partitioningBy(student -> student.getScore() > 90));
          System.out.println(listMap);
      }
  
      @Data
      @AllArgsConstructor
      public static class Student {
          private String name;
          private Integer score;
          private Integer age;
      }
  }
  ```

- **cellector接口详解：**该接口知识点多参考Collector JAVA DOC文档

  1. 理解Collector<T,A,R> 中三个泛型参数类型的具体含义
  2. 掌握四个方法之间的关系
     -  Supplier<A> supplier();
     - BiConsumer<A, T> accumulator();
     - BinaryOperator<A> combiner();
     - Function<A, R> finisher();

  3. 理解Collector中函数必须满足同一性（identity）和可结合性（associativity）的目的

![image-20200204205135907](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\image-20200204205135907.png)![image-20200204225522197](C:\Users\dennis\AppData\Roaming\Typora\typora-user-images\image-20200204225522197.png)

A中间结果类型  T流中元素类型  R最终返回类型