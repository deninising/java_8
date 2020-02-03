package com.dennis.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述：并发流与串行流
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
