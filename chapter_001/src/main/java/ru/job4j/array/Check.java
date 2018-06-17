package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Check {
    /**
     * Проверяет массив на true.
     *
     * @param data массив который будем проверять.
     * @return результат.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            if (data[0] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
