package ru.job4j.max;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Максимум из двух чисел.
     *
     * @param first  - Первое число.
     * @param second - Второе число.
     * @return Большее число.
     */
    public final int max(final int first, final int second) {
        int result;
        if (first >= second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    /**
     * Максимум из двух чисел.
     *
     * @param first  - Первое число.
     * @param second - Второе число.
     * @param third  - Третье число.
     * @return Большее число.
     */
    public final int max(final int first,
                         final int second,
                         final int third) {
        int temp = this.max((this.max(first, second)), third);
        return temp;
    }
}
