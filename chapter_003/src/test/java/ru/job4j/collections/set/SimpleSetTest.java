package ru.job4j.collections.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<String> set;

    @Before
    public void setUp() throws Exception {
        set = new SimpleSet<>();
        set.add("String1");
        set.add("String2");
        set.add("String3");
        set.add("String4");
        set.add("String5");
    }

    @Test
    public void whenAddDuplicate() {
        assertFalse(set.add("String3"));
    }

    @Test
    public void whenAddSomeOneElement() {
        assertTrue(set.add("String6"));
    }

    @Test
    public void whenContainsString4() {
        assertTrue(set.contains("String4"));
        assertFalse(set.contains("String12"));
    }

    @Test
    public void whenFirstElementIsNull() {
        set.delete(0);
        assertFalse(set.add("String3"));
        assertTrue(set.add("String6"));
        assertTrue(set.contains("String4"));
        assertFalse(set.contains("String12"));
    }
}