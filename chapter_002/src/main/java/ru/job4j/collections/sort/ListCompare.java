package ru.job4j.collections.sort;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {

    //провалился в метод compareTo(String o1, String o2), скопировал и разобрал.
    @Override
    public int compare(String o1, String o2) {
        // n1,n2 переменные принимают длинну строки о1, о2
        int n1 = o1.length();
        int n2 = o2.length();
        //находим минимальную строку,для условия в цикле.
        int min = Math.min(n1, n2);
        //цикл для перебора символов строки через метод charAt()
        for (int i = 0; i < min; i++) {
            char c1 = o1.charAt(i);
            char c2 = o2.charAt(i);
            //Если символы не одинаковы переводим их в аперкейс
            if (c1 != c2) {
                c1 = Character.toUpperCase(c1);
                c2 = Character.toUpperCase(c2);
                //если в верхнем регистре не идентичны переводим в нижний регистр и сравниваем
                if (c1 != c2) {
                    c1 = Character.toLowerCase(c1);
                    c2 = Character.toLowerCase(c2);
                    //если в нижнем не идентичны получаем разницу м/у символами и возвращаем его > 0 или < 0
                    //соответственно понимаем какой символ "больше" , а какой "меньше"
                    if (c1 != c2) {
                        // No overflow because of numeric promotion
                        return c1 - c2;
                    }
                }
            }
        }
        //возвращаем разницу м/у длинами строк если символы идентичны
        //в данном случае получим (больше/меньше 0) или 0 если строки идетичны.
        return n1 - n2;
    }
}
