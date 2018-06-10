package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length; i++) {
            if (data[0] == data[1]) {
                result = true;
            }
        }
        return result;
    }
}
