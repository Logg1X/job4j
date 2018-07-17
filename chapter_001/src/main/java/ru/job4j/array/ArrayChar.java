package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class ArrayChar {
    /**
     * Массив Char.
     */
    private char[] data;

    /**
     * Конгструктор класса.
     *
     * @param line Строка
     */
    public ArrayChar(final String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public final boolean startWith(final String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (value[i] != this.data[i]) {
                result = false;
            }
        }
        return result;
    }
}
