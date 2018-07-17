package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Сортировка "пузырьком".
     *
     * @param array массив, который будем сортировать.
     * @return Отсортированный массив.
     */
    public final int[] sort(final int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}
