package seminar4;

import java.util.ArrayList;

// Пример хэш кода
public class Programm {
    public static void main(String[] args) {
        // справочник - телефон и имя, телефон уникальный ключ
        HashMap<String, String> hashMap1 = new HashMap<>(4);
        String oldValue = hashMap1.put("+79001234546", "AAAAA");
        oldValue = hashMap1.put("+79001234540", "MMMMM");
        oldValue = hashMap1.put("+79001234540", "BBB");
        oldValue = hashMap1.put("+79001234561", "CCC1");
        oldValue = hashMap1.put("+79001234571", "CCC2");
        oldValue = hashMap1.put("+79001234581", "CCC3");
        oldValue = hashMap1.put("+79001234591", "CCC4");
        oldValue = hashMap1.put("+79001234641", "CCC5");
        oldValue = hashMap1.put("+79001234741", "CCC6");
        oldValue = hashMap1.put("+79001234841", "CCC7");
        oldValue = hashMap1.put("+79001234941", "CCC8");

        String res1 = hashMap1.get("+79001234591");
        String res2 = hashMap1.get("+79011234591");

        String res3 = hashMap1.remove("+79001234591");
        String res4 = hashMap1.remove("+79001234641");
        String res5 = hashMap1.remove("+79001234641");

        for (HashMap.Entity e  : hashMap1) {
            System.out.println(e);
        }
    }
}
