package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ru.job4j.Test for Calculator class.
 *
 * @author Toporov Pavel.
 * @version 2.0.
 * @since 05.06.2018.
 */
public class CalculatorTest {
    /**
     * ru.job4j.Test add.
     */
    @Test
    public void wenAddOnePlusOneThenTwo() {
        Calculator calculator = new Calculator();
        calculator.add(1D, 1D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * ru.job4j.Test div.
     */
    @Test
    public void whenDiv2On2Then1() {
        Calculator calculator = new Calculator();
        calculator.div(2D, 2D);
        double result = calculator.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * ru.job4j.Test subtract.
     */
    @Test
    public void whenSubtract4Minus1Then3() {
        Calculator calculator = new Calculator();
        calculator.subtract(4D, 1D);
        double result = calculator.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * ru.job4j.Test multiple.
     */
    @Test
    public void whenMultiple2By3Then6() {
        Calculator calculator = new Calculator();
        calculator.multiple(2D, 3D);
        double result = calculator.getResult();
        double expected = 6D;
    }
}
