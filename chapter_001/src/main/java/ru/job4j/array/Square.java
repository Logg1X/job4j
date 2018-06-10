package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square {
    /**
     * Создет массив целых чисел в квадрате.
     *
     * @param bound количество чисел в массиве.
     * @return массив
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        int temp = 1;
        for (int i = 0; i < rst.length; i++) {
            rst[i] = temp * temp;
            temp++;
        }
        return rst;
    }
}
