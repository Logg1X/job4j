package ru.job4j.array;

public class MatrixCheck {

    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (j == i && data[i][j] != data[0][0]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
