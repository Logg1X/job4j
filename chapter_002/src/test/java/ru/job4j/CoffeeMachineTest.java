package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    CoffeeMachine machine;

    @Before
    public void setUp() throws Exception {
        machine = new CoffeeMachine();
    }

    @Test
    public void whenContributed100AndThePriceIs32() {
        int[] result = this.machine.changes(100, 32);
        int[] expect = new int[]{10, 10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expect));
    }

    @Test
    public void whenContributed150AndThePriceIs35() {
        int[] result = this.machine.changes(50, 35);
        int[] expect = new int[]{10, 5};
        assertThat(result, is(expect));
    }
}