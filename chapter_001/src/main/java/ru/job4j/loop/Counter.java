package ru.job4j.loop;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * Складывает четные числа.
     *
     * @param start  начальное число.
     * @param finish конечное число.
     * @return Результат
     */
    public final int add(int start, final int finish) {
        int count = 0;
        while (start <= finish) {
            if (start % 2 == 0) {
                count += start;
            }
            start++;
        }
        return count;
    }
}

