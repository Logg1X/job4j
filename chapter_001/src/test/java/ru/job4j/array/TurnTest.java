package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    /**
     * Тест turn(int[] array) с четным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[]{1, 2, 3, 4, 5, 6};
        int[] result = turner.turn(input);
        int[] expect = new int[]{6, 5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Тест turn(int[] array) с не четным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[]{1, 2, 3, 4, 5};
        int[] result = turner.turn(input);
        int[] expect = new int[]{5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

}
