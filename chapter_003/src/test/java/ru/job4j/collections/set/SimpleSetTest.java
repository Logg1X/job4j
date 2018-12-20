package ru.job4j.collections.set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    private SimpleSet<String> set;

    @Before
    public void setUp() throws Exception {
        set = new SimpleSet<>(3);
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
}