package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * Переворачиваем массив.
     *
     * @param array исходный массив.
     * @return Перевернутый массив.
     */
    public final int[] turn(final int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        return array;
    }
}
