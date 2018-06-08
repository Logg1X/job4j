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
        int temp = this.max((this.max(first, second)), third);
        return temp;
    }
}