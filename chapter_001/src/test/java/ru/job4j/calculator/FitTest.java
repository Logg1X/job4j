package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * ru.job4j.Test for fit task
 *
 * @version 2.1
 * @autor Toporov Pavel
 * @since 06.06.18
 */
public class FitTest {
    /**
     * ru.job4j.Test manWeight
     */
    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        assertThat(weight, closeTo(92.0, 0.1));
    }

    /**
     * ru.job4j.Test womanWeight
     */
    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeight(170);
        assertThat(weight, closeTo(69.0, 0.1));
    }

}
