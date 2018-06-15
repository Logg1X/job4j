package ru.job4j.array;

public class MatrixCheck {

    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][i] == result && data[i][data.length - 1 - i] == result) {
                    result = true;
                } else result = false;
            }
        }
        return result;
    }
}
//data[i][i] != data[0][0] &