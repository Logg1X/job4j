package ru.job4j.array;


import java.util.Arrays;
/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1*/
public class ArrayDuplicate {
    /**
     * Удаляет дубликаты в массиве строк.
     * @param array Массив.
     * @return Массив без дубликатов.*/
    public final String[] remove(final String[] array) {
        int dub = array.length;
        for (int i = 0; i < dub; i++) {
            for (int j = i + 1; j < dub; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[dub - 1];
                    dub--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, dub);
    }

}
