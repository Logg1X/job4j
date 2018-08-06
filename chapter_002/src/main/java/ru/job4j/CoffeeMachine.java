package ru.job4j;


import java.util.Arrays;

public class CoffeeMachine {
    final int[] coins = {10, 5, 2, 1};


    public int[] changes(int value, int price) {
        int[] result = new int[100];
        int position = 0;
        if (value != price && value > price) {
            int res = value - price;
            for (int i : coins) {
                while (res / i != 0) {
                    res -= i;
                    result[position++] = i;
                }
            }
        }
        return Arrays.copyOf(result, position);
    }
}

