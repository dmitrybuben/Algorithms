package seminar3;

import java.util.Comparator;

// Связный список. Обобщенный тип для разных типов данных (универсальный параметр Т)
public class LinkedList<T> {
    /**
     * Указатель на первый элемент связного списка
     */
    private Node head;

    /**
     * Вложенный класс пример - для описания Node используется только внутри LinkedList
     */
    class Node {
        /**
         * Ссылка на следующий элемент
         */
        public Node next;
        /**
         * Значение узла
         */
        public T value;

    }
// Методы для работы со связным списком

    /**
     * Добавление нового элемента в начало списка
     *
     * @param value значение
     */
    public void addFirst(T value) {
// добавление нового узла
        Node node = new Node();
// присваиваем ему новое значение
        node.value = value;
        if (head != null) {
//  Ссылка нового элемента на первый существующий (т.к. head - это ссылка)
            node.next = head;
        }
//  Тут ссылка head в любом случае становится ссылкой на новую ноду - затираем старую ссылку на бывшую
//  первую ноду или затираем ссылку head на null - если первой ноды не было.
        head = node;
    }

    /**
     * Удаление первого элемента списка
     */
    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    /**
     * Поиск элемента в списке по значению
     *
     * @param value значение
     * @return
     */
    public T contains(T value) {
//  Создаем переменную node и пока она не будет указвать на null (нет далее узлов) присваиваем ей ссылку
//  на следующий node - node next
        Node node = head;
        while (node != null) {
            if (node.value.equals(value)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Сортировка (выбором)
     */
    public void sort(Comparator<T> comparator) {
        Node node = head;
        while (node != null) {
            Node minValueNode = node;
            Node node2 = node.next;
            while (node2 != null) {
                if (comparator.compare(minValueNode.value, node2.value) > 0) {
                    minValueNode = node2;
                }
                node2 = node2.next;
            }
//  Если поменялось минимальное значение - не меняем местами ноды, а меняем их значения!
            if (minValueNode != node) {
                T buf = node.value;
                node.value = minValueNode.value;
                minValueNode.value = buf;
            }
            node = node.next;
        }
    }

    /**
     * Добавление нового элемента в конец связного списка
     * @param value значение
     */
    public void addLast(T value){
        Node node = new Node();
        node.value = value;
        if (head == null){
            head = node;
        }
        else {
            Node lastNode = head;
            while (lastNode.next != null){
                lastNode = lastNode.next;
            }
            lastNode.next = node;
        }
    }

    /**
     * Удаление последнего элемента из связного списка
     */
    public void removeLast() {
//  Когда элементов нет - выходим
        if (head == null){
            return;
        }
// Если элемент 1 - сразу после цикла node = null - удалили.
        Node node = head;
// Когда нод несколько:
        while (node.next != null) {
//  Если третьей ноды не существует - удаляем вторую node.next == null;
            if (node.next.next == null){
                node.next = null;
                return;
            }
            node = node.next;
        }
        head = null;
    }

    /**
     * Метод разворота связного списка
     */
    public void reverse(){
        if (head == null) {
            return;
        }
        Node prev = null;
        Node curr = head;
        Node next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    @Override
        public String toString () {
            StringBuilder stringBuilder = new StringBuilder();
            Node node = head;
            while (node != null) {
                stringBuilder.append(node.value);
//  Добавляем перевод на новую строку
                stringBuilder.append('\n');
                node = node.next;
            }
            return stringBuilder.toString();
        }
    }
