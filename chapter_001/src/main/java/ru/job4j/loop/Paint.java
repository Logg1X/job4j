package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    /**
     * Строит левую-пирамиду.
     *
     * @param height Количество строк.
     * @return левая полу-пирамида.
     */
    public final String leftTrl(final int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Строит правую полу-пирамиду.
     *
     * @param height Количество строк.
     * @return правая полу-пирамида.
     */
    public final String rightTrl(final int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Строит пирамиду.
     *
     * @param height Количество строк.
     * @return Пирамида.
     */
    public final String pyramid(final int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1
                        && row + height - 1 >= column
        );
    }

    /**
     * Вспомогательный универсаньный метод.
     *
     * @param height  количество строк.
     * @param weight  ширина.
     * @param predict функциональный интерфейс BiPredicate.
     * @return Результат .
     */
    private String loopBy(final int height,
                          final int weight,
                          final BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}

