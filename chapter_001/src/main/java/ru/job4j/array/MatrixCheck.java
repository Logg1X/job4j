package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class MatrixCheck {

    /**
     * Проверка диагоналей массива на одинаковые значения.
     *
     * @param data Массив для проверки.
     * @return результат проверки.
     */
    public final boolean mono(final boolean[][] data) {
        int trueCount = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][i] == data[0][0] && data[i][data.length - 1 - i]
                        == data[0][data.length - 1]) {
                    trueCount++;
                    break;
                }
            }
        }
        return data.length == trueCount;
    }
}
