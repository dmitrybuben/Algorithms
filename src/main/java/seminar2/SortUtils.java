package seminar2;

public class SortUtils {
    /**
     * Пример реализации сортировки выбором DirectSort
     */
    public static void directSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int saveIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[saveIndex]) {
                    saveIndex = j;
                }
            }
            if (saveIndex != i) {
                int buf = array[i];
                array[i] = array[saveIndex];
                array[saveIndex] = buf;
            }

            /* Поменять местами значения переменных без ввода третьей
             * int a = 10;
             * int b = 20;
             * a = a + b; а = 30, b = 20
             * b = a - b; b = 10, a = 30
             * a = a - b; a = 20, b = 10
             */
        }
    }
//      Быстрая сортировка - выбрать опорный элемент и двигаться к нему слева и справа,
//      перемещая элементы > опорного в правую часть, а меньше - в левую.
//      Сложность в худшем случае О(n^2) в лучшем - О(n) - только внешний цикл будет крутиться.

    public static void quickSort(int[]array){
        quickSort(array,0, array.length-1);
    }

    private static void quickSort(int[]array, int start, int end){
//        1 этап алгоритма - определение опорного элемента (середина диапазона для сортировки)
        int left = start; // сохраняем в отдельные переменные исходные индексы начала и конца
        int right = end;
        int middle = array[(start + end) / 2]; // значение опорного элемента

        do {
//        2 часть алгоритма
//        Двигаемся по индексам слева и справа
            while (array[left] < middle) {
                left++;
            }
            while (array[right] > middle) {
                right--;
            }
//        меняем местами значения элементов по индексам слева и справа
            if (left <= right) {
                if (left < right) {
                    int buf = array[left];
                    array[left] = array[right];
                    array[right] = buf;
                }
                left++;
                right--;
            }
        }
        while (left <= right);

//        3 часть алгоритма делим исходный массив на 2 части и рекурсивно вызываем метод quickSort
//        на этих двух частях до тех пор, пока менять местами будет нечего и сортировка закончится

        if (left < end){
            quickSort(array, left, end);
        }
        if (start < right){
            quickSort(array, start, right);
        }
    }
}
