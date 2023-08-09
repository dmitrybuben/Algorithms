package seminar3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programm {
    public static void main(String[] args) {
        LinkedList<Employee> linkedList = new LinkedList<>();
        linkedList.addFirst(new Employee("AAAA", 40));
        linkedList.addFirst(new Employee("BBBB", 41));
        linkedList.addFirst(new Employee("CC", 43));
        linkedList.addFirst(new Employee("BBBB", 55));
        linkedList.addFirst(new Employee("BBBB", 21));
        linkedList.addFirst(new Employee("BBBB", 33));
        linkedList.addFirst(new Employee("DDD", 46));

//  В дебаггере e равен DDD + 46
        /*Employee e = linkedList.contains(new Employee("DDD", 46));
        System.out.println(linkedList);*/

/*      ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 5, 10, 11));
        System.out.println(arrayList);

        int[]array1 = new int[]{1, 5, -6, 7, -8};
        Arrays.sort(array1);
        System.out.println(Arrays.toString(array1));*/

        /*linkedList.sort(new EmployeeComparator(SortType.Ascending));
        System.out.println(linkedList);

        linkedList.sort(new EmployeeComparator(SortType.Descending));
        System.out.println(linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();*/

        System.out.println(linkedList);

        linkedList.reverse();

        System.out.println(linkedList);

    }
}
