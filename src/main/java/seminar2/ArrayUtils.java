package seminar2;

import java.util.Random;

/**
 * Утилиты для работы с массивом
 */

public class ArrayUtils {

    //Поле для генерации случайных чисел
    private static Random random = new Random();

    /**
     * Подготовить массив случайной размерности
     * @return
     */
    public static int[] prepareArray(){
        return prepareArray(random.nextInt(10, 16));
    }

    /**
     * Подготовить массив случайных чисел
     * @param lenght размерность
     * @return
     */
    public static int[] prepareArray(int lenght){
        int[]array = new int[lenght];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-99,100);
        }
        return array;
    }

    /**
     * Распечатать массив в консоль через табуляцию \t
     * @param array массив
     */
    public static void printArray(int[]array){
        for (int element : array){
            System.out.printf("%d\t", element);
        }
        System.out.println();
    }
}
