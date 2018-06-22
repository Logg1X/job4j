package ru.job4j.tictactoe;

import java.util.function.Predicate;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 1.0
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Проверяет Победил ли "Х"
     */
    public boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Проверяет победил ли "О"
     */
    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Проверяет есть ли еще доступные ходы.
     */
    public boolean hasGap() {
        boolean hasGap = false;
        if (!isWinnerX() && !isWinnerO()) {
            for (int i = 0; i < this.table.length; i++) {
                for (int j = 0; j < this.table.length; j++) {
                    if (!this.table[i][j].hasMarkX() && !this.table[i][j].hasMarkO()) {
                        hasGap = true;

                    }
                }
            }

        }
        return hasGap;
    }

    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
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



/*    private boolean checkVerticalLineX() {
        int trueCount = 0;
        int temp = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (trueCount != 3) {
                trueCount = 0;
                temp = 0;
            } else {
                break;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (table[temp][i].hasMarkX()) {
                    trueCount++;
                }
                temp++;
            }
        }
        return trueCount == 3;
    }

    private boolean checkFirstDiagonalsX() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][i].hasMarkX()) {
                    trueCount++;
                    break;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkSecondDiagonalsX() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][table.length - 1 - i].hasMarkX()) {
                    trueCount++;
                    break;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkGorisontalLineX() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (trueCount != 3) {
                trueCount = 0;
            } else {
                break;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j].hasMarkX()) {
                    trueCount++;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkVerticalLineO() {
        int trueCount = 0;
        int temp = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (trueCount != 3) {
                trueCount = 0;
                temp = 0;
            } else {
                break;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (table[temp][i].hasMarkO()) {
                    trueCount++;
                }
                temp++;
            }
        }
        return trueCount == 3;
    }

    private boolean checkFirstDiagonalsO() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][i].hasMarkO()) {
                    trueCount++;
                    break;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkSecondDiagonalsO() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][table.length - 1 - i].hasMarkO()) {
                    trueCount++;
                    break;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkGorisontalLineO() {
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            if (trueCount != 3) {
                trueCount = 0;
            } else {
                break;
            }
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j].hasMarkO()) {
                    trueCount++;
                }
            }
        }
        return trueCount == 3;
    }*/
}


