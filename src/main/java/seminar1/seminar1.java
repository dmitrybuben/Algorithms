package seminar1;import java.util.ArrayList;import java.util.concurrent.atomic.AtomicInteger;public class seminar1 {    public static void main(String[] args) {        int lastNumber = 20;        AtomicInteger counter = new AtomicInteger();//одним циклом - линейная        System.out.printf("(1 variant): All numbers sum from 1 to %d is equal to %d\n", lastNumber, sum(lastNumber, counter));        System.out.println("Number of iterations is: " + counter.get());//без итераций - одним действием - О(1)        System.out.printf("(2 variant): All numbers sum from 1 to %d is equal to %d\n", lastNumber, sum2(lastNumber));//поиск простых чисем - квадратичная        counter.set(0); // обнуляем ранее использованный counter        System.out.printf("Simple numbers from 1 to %d: \n%s",                lastNumber, findSimpleNumbers(lastNumber, counter));        System.out.println("Number of iterations is: " + counter.get());        f(4);//метод через рекурсию - экспоненциальная        counter.set(0);        long startTime = System.currentTimeMillis();        System.out.printf("Fibonachi number for index %d is %d (recurcion method): \n",                lastNumber, fb1(lastNumber,counter));        System.out.println("Number of iterations is: " + counter.get());        long endTime = System.currentTimeMillis();        System.out.printf("Operation time is %d ms.\n", endTime-startTime);//метод без рекурсии - линейная        counter.set(0);        startTime = System.currentTimeMillis();        System.out.printf("Fibonachi number for index %d is %d (Without recurcion method): \n",                lastNumber, fb2(lastNumber,counter));        System.out.println("Number of iterations is: " + counter.get());        endTime = System.currentTimeMillis();        System.out.printf("Operation time is %d ms.\n", endTime-startTime);    }    /*    Алгоритм, считающий сумму всех чисел от 1 до N - сложность O(n) - линейная    + Пример работы ссылочного типа данных AtomicInteger    */    public static int sum(int lastNumber, AtomicInteger counter ) {        int sum = 0;        for (int i = 1; i <= lastNumber; i++) {            sum += i;            counter.getAndIncrement();        }        return sum;    }    /*    * Второй способ - без итераций, Сложность - константная O(1) не зависит от входных данных.    * */    public static int sum2(int lastNumber){        return ((lastNumber+1)/2)*lastNumber;    }    /*    Алгоритм поиска простых чисел - деляться только на себя и на 1 без остатка    Сложность (парабола) называется квадратичной (2 вложенных цикла),    может быть и в кубе (3 вложенных цикла) и т.д.    */    /**     *     * @param lastNumber число, до которого ищем простые числа     * @param counter счетчик числа итераций     * @return     */    public static ArrayList<Integer> findSimpleNumbers(int lastNumber,AtomicInteger counter){        ArrayList<Integer> arrayList = new ArrayList<>();        boolean simple;        for (int i = 1; i <= lastNumber; i++) {            simple = true;            for (int j = 2; j < i; j++) {                counter.getAndIncrement(); // counter +1                if (i % j == 0){                    simple = false;                    break;                }            }            if (simple){                arrayList.add(i);            }        }        return arrayList;    }    // Рекурсия - как проверить? берем минимальное значение и расписываем.    static void f(int n){        System.out.println(n);        if (n >=3){            f(n - 1);            f(n - 2);            f(n - 2);        }    }/** * Алгоритм поиска нужного числа в последовательности Фибоначчи по индексу * * 0 1 2 3 4 5 6  7  8  9 10 * 0 1 1 2 3 5 8 13 21 34 55 ... * * Сложность будет экспоненциальная О(e в степени n), e - константа */    static long fb1(int index, AtomicInteger counter){        counter.getAndIncrement(); // 1        if (index == 0 || index == 1){            return index;        }        return fb1(index - 1, counter) + fb1(index - 2, counter);    }    /**     * Тот же алгоритм с помощью массива     */    static long fb2(int index, AtomicInteger counter) {        if (index == 0 || index == 1) {            return index;        }        long[] numbers = new long[index + 1];        numbers[0] = 0;        numbers[1] = 1;        for (int i = 2; i < numbers.length; i++) {            counter.getAndIncrement();            numbers[i] = numbers[i - 1] + numbers[i - 2];        }        return numbers[index];    }}