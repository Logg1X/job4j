package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PaintTest {
    private final PrintStream stream = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stream);
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator(), "",
                        System.lineSeparator())
                        .add("####")
                        .add("#  #")
                        .add("#  #")
                        .add("#  #")
                        .add("####")
                        .toString()
                )
        );
        System.setOut(stream);
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator(), "",
                        System.lineSeparator())
                        .add("   #   ")
                        .add("  ###  ")
                        .add(" ##### ")
                        .add("#######")
                        .toString()
                )
        );
        System.setOut(stream);
    }
}

