package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = false;
        int trueCount = 0;
        int falseCount = 0;
        for (boolean datum : data) {
            if (datum) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        if (trueCount == 0 || falseCount == 0) {
            result = true;
        }
        return result;
    }
}
