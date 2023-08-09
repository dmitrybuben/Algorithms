package seminar2;

public class Program {
    public static void main(String[] args) {
        int[] array = ArrayUtils.prepareArray();
        ArrayUtils.printArray(array);
        SortUtils.directSort(array);
        ArrayUtils.printArray(array);

        System.out.println();

        array = ArrayUtils.prepareArray();
        ArrayUtils.printArray(array);
        SortUtils.quickSort(array);
        ArrayUtils.printArray(array);

        System.out.println();

        array = new int[]{5, 4, 1, 0, 1};
        ArrayUtils.printArray(array);
        SortUtils.directSort(array);
        ArrayUtils.printArray(array);

        array = ArrayUtils.prepareArray(1500);
        long startTime = System.currentTimeMillis();
        SortUtils.quickSort(array.clone());
        long endTime = System.currentTimeMillis();
        System.out.printf("DirectSort time is %d ms.\n", endTime - startTime);

        array = ArrayUtils.prepareArray(1500);
        startTime = System.currentTimeMillis();
        SortUtils.quickSort(array.clone());
        endTime = System.currentTimeMillis();
        System.out.printf("QuickSort time is %d ms.\n", endTime - startTime);

        System.out.println();

        array = new int[]{-5, 10, 4, 10, 8, 7, -9, 11, 40, 90, 100};
        ArrayUtils.printArray(array);
        SortUtils.quickSort(array);
        ArrayUtils.printArray(array);
        int searchElement = 10;
        int result = SearchUtils.binarySearch(searchElement, array);
        System.out.printf("Element %d %s", searchElement,
                result >= 0 ? String.format("found under index %d", result) :
                "not found");
    }
}
