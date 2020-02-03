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
