package seminar2;

public class HeapSort {
    public static void main(String[] args) {
        int[]array = new int[]{4,-10,1,5,3,7};
        System.out.println("Not sorted array:");
        printArray(array);
        System.out.println();
        sort(array);

        System.out.println("Sorted array:");
        printArray(array);
    }

    //  Построение кучи с самым большим наверху, корень - середина массива
    public static void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }
//  Меняеем местами самый большой элемент - корень с самым последним узлом и убираем
// последний узел с большим элементом
        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

//  Просеивание на меньшенной куче
            heapify(array, i, 0);
        }
    }
//  Просеивание - самое большое значение - наверх на нулевую позицию массива
    public static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = largest * 2 + 1;
        int right = largest * 2 + 2;

        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

//  Перенос самого большого наверх
        if (largest != rootIndex){
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

//  Смотрим что изменилось у низлежащих элементов
            heapify(array, heapSize, largest);
        }
    }

    public static void printArray(int[]array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}



