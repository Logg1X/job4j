package ru.job4j.strategy;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
                                .toString()));
        System.setOut(stream);

    }
}
