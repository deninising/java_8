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
