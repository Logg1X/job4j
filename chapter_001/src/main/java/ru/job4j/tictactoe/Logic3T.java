package ru.job4j.tictactoe;


public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {

        return checkGorisontalLine();
    }

    public boolean isWinnerO() {
        return false;
    }

    public boolean hasGap() {
        return true;
    }

    private boolean chekLineGorisontal() {
        boolean result = false;
        int trueCount = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][i].hasMarkX() || table[i][table.length - 1 - i].hasMarkX()) {
                    trueCount++;
                    break;
                }
            }
        }

        return result;
    }

    private boolean checkDiagonals() {
        boolean result = false;
        int trueCount = 0;

        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][i].hasMarkX()
                        || table[i][table.length - 1 - i].hasMarkX()) {
                    trueCount++;
                    break;
                }
            }
        }
        return trueCount == 3;
    }

    private boolean checkGorisontalLine() {
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

}


//|| table[i][0].hasMarkX()
//        || table[i][1].hasMarkX()
//        || table[i][2].hasMarkX()
//        || table[0][j].hasMarkX()
//        || table[1][j].hasMarkX()
//        || table[2][j].hasMarkX()

