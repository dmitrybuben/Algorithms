package org.lecture1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

    public static void main(String[] args) {
        //List<Integer> availableDivider = findAvailableDivider(16);
        //List<Integer> availableDivider = findSimpleNumbers(16);
        /*for (Integer integer : availableDivider) {
            System.out.println(integer);
        }*/
        AtomicInteger counter = new AtomicInteger(0);
        int fib = fib(10, counter);
        System.out.println("Fib number: " + fib);
        System.out.println("Counter: " + counter.get());

        counter = new AtomicInteger(0);
        fib = fib(11, counter);
        System.out.println("Fib number: " + fib);
        System.out.println("Counter: " + counter.get());

        counter = new AtomicInteger(0);
        fib = fib(12, counter);
        System.out.println("Fib number: " + fib);
        System.out.println("Counter: " + counter.get());
    }

    // Поиск допустимых делителей для числа (без остатка)
    // Пример линейной зависимости - кол-во шагов зависит напрямую от входящих данных - число 12 и 12 шагов
    public static List<Integer> findAvailableDivider(int number) {
        int counter = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            counter++;
            if (number % i == 0) {
                result.add(i);
            }
        }
        System.out.println("Counter = " + counter);
        return (result);
    }

    // поиск простых чисел на опред.отрезке до значения max
    public static List<Integer> findSimpleNumbers(int max) {
        int counter = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            boolean simple = true;
            for (int j = 2; j < i; j++) {
                counter++;
                if (i % j == 0) {
                    simple = false;
                    /* можно сократить алгоритм, добавив break если число не простое, но все равно видна
                    квадратичность, хоть и не точная, сложности.*/
                    break;
                }
            }
            if (simple) {
                result.add(i);
            }
        }
        System.out.println("Counter = " + counter);
        return result;
    }

    // поиск значения Фибоначчи для заданной позиции
    public static int fib(int position, AtomicInteger counter) {
        counter.incrementAndGet();
        if (position == 1) {
            return 0;
        }
        if (position == 2) {
            return 1;
        }
        return fib(position - 1, counter) + fib(position - 2, counter);
    }
}