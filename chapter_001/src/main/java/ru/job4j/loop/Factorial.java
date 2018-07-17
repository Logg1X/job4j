package ru.job4j.loop;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Вычисление факториала.
     *
     * @param n факториал числа n.
     * @return Факториал.
     */
    public final int calc(final int n) {
        int calc = 1;
        for (int i = 1; i <= n; i++) {
            calc *= i;
        }
        return calc;
    }
}

