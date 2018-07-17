package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */

public class Matrix {
    /**
     * Таблица умножения.
     *
     * @param size Размерность таблицы (например 10х10)
     * @return Таблица умножения.
     */
   public final int[][] multiple(final int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
