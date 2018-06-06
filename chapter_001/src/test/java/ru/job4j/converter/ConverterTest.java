package ru.job4j.converter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Converter class.
 *
 * @author Toporov Pavel.
 * @version 1.0.
 * @since 06.06.2018.
 */
public class ConverterTest {
    /**
     * Test rubleToDollar.
     */
    @Test
    public void when124RublesToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(124);
        assertThat(result, is(2));
    }
    /**
     * Test rubleToEuro.
     */
    @Test
    public void when146RublesToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(146);
        assertThat(result, is(2));
    }
    /**
     * Test euroToRubles.
     */
    @Test
    public void when2EuroToRublesThen146() {
        Converter converter = new Converter();
        int result = converter.euroToRubles(2);
        assertThat(result, is(146));
    }
    /**
     * Test dollarToRubles.
     */
    @Test
    public void when2DollarsToRublesThen124() {
        Converter converter = new Converter();
        int result = converter.dollarToRubles(2);
        assertThat(result, is(124));
    }


}
