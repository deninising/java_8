package com.dennis.jdk8.streamdeeplearn;

/**
 * 描述：通过实现AutoCloseable接口，和采用try-with-resource代码块的方式声明将使用
 * 的对象，在执行完该代码块时，声明对象中的close()方法将自动被调用以释放资源
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/2/13 22:12
 */
public class MyAutoCloseableImpl implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("automatically invoke close to release resource ");
    }

    private void doSth() {
        System.out.println("to do something with the resource holding object ...");
    }

    public static void main(String[] args) throws Exception {
        try (MyAutoCloseableImpl myImpl = new MyAutoCloseableImpl()) {
            // todo
            myImpl.doSth();
            // todo
            System.out.println("代码块将执行完毕。。。");
        }
        System.out.println("代码块已执行完毕。。。");
    }

}
