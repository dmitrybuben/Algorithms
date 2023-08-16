package seminar4;

import java.util.Iterator;

public class HashMap<K, V> implements Iterable<HashMap.Entity> {
    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;

    // Истинное кол-во (реально добавленных элементов, а не перезатертых если ключ совпал)
    // значение элементов хэш-таблицы
    private int size;

    // Хэш таблица имеет в основе массив типа бакетов (связных списков)
    private Bucket[] buckets;
    private Bucket.Node currentNode;
    private int currentIndex;

    @Override
    public Iterator<HashMap.Entity> iterator() {
        return new HashMapIterator();
    }

    class HashMapIterator implements Iterator<HashMap.Entity> {
        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                for (int i = 0; i < buckets.length; i++)
                    if (buckets[i] != null && buckets[i].head != null) {
                        currentIndex = i;
                        currentNode = buckets[i].head;
                        return true;
                    }
                return false;
            }
            else{
                if (get((K)currentNode.value.key) == null)
                {
                    currentNode = null;
                    currentIndex = 0;
                    return hasNext();
                }
                else{
                    HashMap.Bucket.Node node = currentNode;
                    currentIndex = calculateBucketIndex((K)node.value.key);
                    if (node.next != null){
                        currentNode = node.next;
                        return true;
                    }
                    for (int i = ++currentIndex; i < buckets.length; i++){
                        if (buckets[i] != null && buckets[i].head != null){
                            currentIndex = i;
                            currentNode = buckets[i].head;
                            return true;
                        }
                    }
                    currentNode = null;
                    currentIndex = 0;
                    return false;
                }
            }
        }
        @Override
        public Entity next() {
            if (currentNode == null){
                for (int i = 0; i < buckets.length; i++)
                    if (buckets[i] != null && buckets[i].head != null){
                        currentIndex = i;
                        currentNode = buckets[i].head;
                        return currentNode.value;
                    }
                return null;
            }
            else if (get((K)currentNode.value.key) == null){
                currentNode = null;
                currentIndex = 0;
                return null;
            }
            else
                return currentNode.value;
        }
    }

        /**
         * Сущность, которая объединяет ключ-значение - элемент хэш-таблицы
         */
        class Entity {
            K key;
            V value;

            @Override
            public String toString() {
                return String.format("%s - %s", key, value);
            }
        }

        /**
         * Связный список
         *
         * @param <K> Ключ элемента хэш-таблицы
         * @param <V> Значение элемента хэш-таблицы
         */
        class Bucket<K, V> {
            // Указатель на 1 элемент связного списка
            Node head;

            /**
             * Узел внутри бакета (связного списка)
             */
            class Node {

                // Указатель на следующий элемент бакета (связного списка)
                Node next;
                // Значение узла, указывающее на элемент хэш-таблицы
                Entity value;
            }

            /**
             * Добавление нового элемента хэш-таблицы - возвращает null если ключ entity уникальный и перезаписывать
             * существующий элемент не пришлось, возвращает значение существ. эл-та, если он с таким ключом уже существует
             *
             * @param entity элемент хэш-таблицы
             * @return
             */
            public V add(Entity entity) {
                Node node = new Node();
                node.value = entity;

                if (head == null) {
                    head = node;
                    return null;
                }
                // Вечный цикл для прохода по всем элементам связного списка - backet и проверки значения ключа
                Node currentNode = head;
                while (true) {
                    if (currentNode.value.key.equals(entity.key)) {
                        //сохраняем значение уже присутствующего элемента и потом возвращает его.
                        V buf = (V) currentNode.value.value; // каст к типу обобщенного параметра
                        currentNode.value.value = entity.value; // меняем значение существ. эл-та на знач. добавляемого эл-та
                        return buf;
                    }
                    // смотрим ноды до конца
                    if (currentNode.next != null) {
                        currentNode = currentNode.next;
                    }
                    // если дошли до конца и значение ключа уникально - добавляем в конец entity и возвращаем null
                    else {
                        currentNode.next = node;
                        return null;
                    }
                }
            }

            public V get(K key) {
                Node node = head;
                while (node != null) {
                    if (node.value.key.equals(key)) {
                        return (V) node.value.value;
                    }
                    node = node.next;
                }
                return null;
            }

            public V remove(K key) {
                if (head == null)
                    return null;
                if (head.value.key.equals(key)) {
                    V buf = (V) head.value.value;
                    head = head.next;
                    return buf;
                } else {
                    Node node = head;
                    while (node.next != null) {
                        if (node.next.value.key.equals(key)) {
                            V buf = (V) node.next.value.value;
                            // удалаяем ноду
                            node.next = node.next.next;
                            return buf;
                        }
                        node = node.next;
                    }
                    return null;
                }
            }
        }

        // Метод вычисляет индекс элемента массива бакетов чтобы понять куда методу put положить пару ключ-значение
        private int calculateBucketIndex(K key) {
            return Math.abs(key.hashCode()) % buckets.length;
        }

        private void recalculate() {
            size = 0;
            // Вспомогательная переменная - старый бакет, наполовину полный
            Bucket<K, V>[] old = buckets;
            buckets = new Bucket[old.length * 2];
            for (int i = 0; i < old.length; i++) {
                Bucket<K, V> bucket = old[i];
                if (bucket != null) {
                    Bucket.Node node = bucket.head;
                    while (node != null) {
                        put((K) node.value.key, (V) node.value.value);
                        node = node.next;
                    }
                }
            }
        }

        // Метод добавления элемента - возвращает значение V если перезапись и null если перезапись не потребовалась.

        // ПЕРЕД добавлением новоо элемента - ограничение длинны связного списка - желательно до 1 элемента:
        // 4 * 0,5 = 2 > 0 (элементов пока нет) - добавляем первый элемент и далее пока не будет <= size и
        // надо будет расширить массив в 2 раза и произвести его рекалькуляцию - метод recalculate
        public V put(K key, V value) {
            if (buckets.length * LOAD_FACTOR <= size) {
                recalculate();
            }

            int index = calculateBucketIndex(key);
            //Создадим объект если в массиве по этому индексу элемент указывает на null
            Bucket bucket = buckets[index];
            if (bucket == null) {
                bucket = new Bucket();
                buckets[index] = bucket;
            }
            //Объединим ключ и значение
            Entity entity = new Entity();
            entity.key = key;
            entity.value = value;

            // Контроль кол-ва добавляемых в бакет узлов - Если null, то есть добавили именно новый элемент -
            // кол-во увеличиваем на 1 и возвращаем промежуточное значение buf.
            V buf = (V) bucket.add(entity);
            if (buf == null) {
                size++;
            }
            return buf;
        }

        public V get(K key) {
            int index = calculateBucketIndex(key);
            Bucket bucket = buckets[index];
            if (bucket == null)
                return null;
            return (V) bucket.get(key);
        }

        public V remove(K key) {
            int index = calculateBucketIndex(key);
            Bucket bucket = buckets[index];
            if (bucket == null)
                return null;
            V buf = (V) bucket.remove(key);
            if (buf != null) {
                size--;
            }
            return buf;
        }

        // добавим 2 конструктора - 1 сколько элементов хоти увидеть в начальном массиве баскетов (в хэш-таблице)
        // 2 - если не будет задан размер просто с использованием константы INIT_BUCKET_COUNT будем формировать массив так

        public HashMap() {
            buckets = new Bucket[INIT_BUCKET_COUNT];
        }

        public HashMap(int initCount) {
            buckets = new Bucket[initCount];
        }

    }
