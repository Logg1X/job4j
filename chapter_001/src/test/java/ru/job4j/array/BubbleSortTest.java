package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[]{5, 7, 3, 2, 1, 4, 6};
        int[] result = bubbleSort.sort(input);
        int[] expect = new int[]{1, 2, 3, 4, 5, 6, 7};
        assertThat(result, is(expect));
    }
}
