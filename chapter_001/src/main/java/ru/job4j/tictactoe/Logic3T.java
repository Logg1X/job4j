package ru.job4j.tictactoe;

import java.util.function.Predicate;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 1.0
 */
public class Logic3T {
    /**
     * Поле для игры.
     */
    private final Figure3T[][] table;

    /**
     * Конструктор класса.
     *
     * @param tableXO поле для игры.
     */
    public Logic3T(final Figure3T[][] tableXO) {
        this.table = tableXO;
    }

    /**
     * Проверяет Победил ли "Х".
     *
     * @return tru - если побудил О. Иначе false
     */
    public final boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX,
                this.table.length - 1, 0, -1, 1);
    }

    /**
     * Проверяет победил ли "О".
     *
     * @return tru - если побудил О. Иначе false
     */
    public final boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO,
                this.table.length - 1, 0, -1, 1);
    }

    /**
     * Проверяет есть ли еще доступные ходы.
     *
     * @return tru - если ходы есть. Иначе false
     */
    public final boolean hasGap() {
        boolean hasGap = false;
        if (!isWinnerX() && !isWinnerO()) {
            for (int i = 0; i < this.table.length; i++) {
                for (int j = 0; j < this.table.length; j++) {
                    if (!this.table[i][j].hasMarkX()
                            && !this.table[i][j].hasMarkO()) {
                        hasGap = true;

                    }
                }
            }

        }
        return hasGap;
    }

    /**
     * Универсальный метод ля проверки ячейки.
     *
     * @param predicate Интерфейс Predicate
     * @param startX    начальная точка по оси Х.
     * @param startY    начальная точка по оси Y.
     * @param deltaX    направление движения по оси Х.
     * @param deltaY    направление движения по оси Y.
     * @return true если ячейки содержат одинаковую фигуру.
     * Иначе false.
     */
    private boolean fillBy(final Predicate<Figure3T> predicate,
                           int startX,
                           int startY,
                           final int deltaX,
                           final int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }
}


