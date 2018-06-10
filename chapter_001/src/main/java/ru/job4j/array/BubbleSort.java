package ru.job4j.array;

public class BubbleSort {
    public int[] sort(int[] array) {
        int i = 0;
        int goodPairsCounter = 0;
        while (true) {
            if (array[i] > array[i + 1]) {
                int q = array[i];
                array[i] = array[i + 1];
                array[i + 1] = q;
                goodPairsCounter = 0;
            } else {
                goodPairsCounter++;
            }
            i++;
            if (i == array.length - 1) {
                i = 0;
            }
            if (goodPairsCounter == array.length - 1) {
                break;
            }
        }

        return array;
    }


}
