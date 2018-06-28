package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PaintTest {

    @Test
    public void whenDrawSquare() {
        PrintStream stream = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        out.toByteArray();
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append("####").append(System.lineSeparator())
                        .append("#  #").append(System.lineSeparator())
                        .append("#  #").append(System.lineSeparator())
                        .append("#  #").append(System.lineSeparator())
                        .append("####").append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
                System.setOut(stream);

    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stream = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        out.toByteArray();
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append("   #   ").append(System.lineSeparator())
                        .append("  ###  ").append(System.lineSeparator())
                        .append(" ##### ").append(System.lineSeparator())
                        .append("#######").append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(stream);

    }
}
