package io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StreamNumbersTest {

    @Test
    public void whenEvenNumberExists() {
        StreamNumbers numbers = new StreamNumbers();
        Integer i = 130;
        InputStream in = new ByteArrayInputStream(new byte[]{i.byteValue()});
        assertTrue(numbers.isNumbers(in));
    }
    @Test
    public void whenEvenNumberIsNotExists() {
        StreamNumbers numbers = new StreamNumbers();
        Integer i = 13;
        InputStream in = new ByteArrayInputStream(new byte[]{i.byteValue()});
        assertFalse(numbers.isNumbers(in));
    }

}