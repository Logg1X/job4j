package ru.job4j.array;

public class MatrixCheck {

    public boolean mono(boolean[][] data) {
        boolean result = true;
        int trueCount = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][i] == data[0][0] && data[i][data.length - 1 - i] == data[0][data.length - 1]) {
                    trueCount++;
                    break;
                }
            }
        }
        return data.length == trueCount;
    }
}
//data[i][i] != data[0][0] &