package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class TriangleTast {
    @Test
    public void whenTriangleDraw() {
        Triangle triangle = new Triangle();
        assertThat(triangle.draw(), is(new StringBuilder()
                .append("   #   ").append(System.lineSeparator())
                .append("  ###  ").append(System.lineSeparator())
                .append(" ##### ").append(System.lineSeparator())
                .append("#######").append(System.lineSeparator())
                .toString()));
    }
}
