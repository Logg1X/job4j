package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void wenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        assertThat(rst, is(new int[]{1, 4, 9}));
    }
    @Test
    public void wenBound1Then1() {
        int bound = 1;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        assertThat(rst, is(new int[]{1}));
    }
    @Test
    public void wen() {
        int bound = 0;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        assertThat(rst, is(new int[]{}));
    }

    /*Очевидно что в таком случае мы получим Exception.
    И понятно, что в рамках данно курса это "рано".
    Просто интересно, на такие случаи пишутся тесты?
    Или такие штуки отлавливаются в "основном" коде блоками Try/catch и не учитываются в тестах ?
    @Test
    public void wenOne() {
        int bound = -1;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        assertThat(rst, is((????)));
    }*/

}
