package ru.job4j.max;
/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * Максимум из двух чисел.
     * @param first - Первое число.
     * @param second - Второе число.
     * @return Большее число.*/
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    public int max(int first, int second, int third) {
        int temp;
        temp = this.max(first, second);
        temp = this.max(temp, third);
        return temp;
    }
}