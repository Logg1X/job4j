package ru.job4j;


import java.util.Arrays;

public class CoffeeMachine {


    public int[] changes(int value, int price) {
        int[] result = new int[100];
        int position = 0;
        if (value != price && value > price) {
            int res = value - price;
            for (int i = 0; res != 0; i++) {
                if (res / 10 >= 1) {
                    res -= 10;
                    result[i] = 10;
                    position++;
                } else if (res / 5 >= 1) {
                    res -= 5;
                    result[i] = 5;
                    position++;
                } else if (res / 2 >= 1) {
                    res -= 2;
                    result[i] = 2;
                    position++;
                } else if (res >= 1) {
                    res -= 1;
                    result[i] = 1;
                    position++;
                }
            }
        }
        return Arrays.copyOf(result, position);
    }
}

