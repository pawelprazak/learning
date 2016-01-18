package com.bluecatcode.learning.codility.lessons;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;

public class Main {

    private static final int BASE_2 = 2;

    public static void main(String[] args) {
        int number = 37;
        System.out.println("#" + Integer.toBinaryString(number));


//        Map<Boolean, List<Integer>> count = digits(number, BASE_2)
//                .peek(e -> System.out.println(": " + e))
//                .collect(Collectors.partitioningBy(n -> n == 0));
        //Collector.of(LongAdder::new, LongAdder::add, LongAdder::intValue)

//        System.out.println("count = " + count);
    }

    private static IntStream digits(int number, int base) {
        long maxSize = digitCount(number, base);
        IntStream powers = IntStream.iterate(number, i -> i /= base).limit(maxSize);
        return powers.map(i -> i % base);
    }

    public static long digitCount(long number, int base) {
        return (long) ((number == 0) ? 1 : logIdentity(abs(number), base) + 1);
    }

    public static double logIdentity(double number, int base) {
        return Math.log(number) / Math.log(base);
    }

}
