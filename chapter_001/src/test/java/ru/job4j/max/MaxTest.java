package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenFirstMoreThenSecond() {
        Max max = new Max();
        int result = max.max(5, 2);
        assertThat(result, is(5));
    }
    @Test
    public void wenFirstLessSecond() {
        Max max = new Max();
        int result = max.max(1, 9);
        assertThat(result, is(9));
    }
}
