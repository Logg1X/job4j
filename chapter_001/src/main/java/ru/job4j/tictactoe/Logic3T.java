package ru.job4j.tictactoe;

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
        return checkFirstDiagonalsX() || checkSecondDiagonalsX() || checkVerticalLineX() || checkGorisontalLineX();
    }

    /**
     * Проверяет победил ли "О"
     */
    public boolean isWinnerO() {
        return checkVerticalLineO() || checkFirstDiagonalsO() || checkSexondDiagonalsO() || checkGorisontalLineO();
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

    private boolean checkVerticalLineX() {
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

    private boolean checkSexondDiagonalsO() {
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
    }

}


