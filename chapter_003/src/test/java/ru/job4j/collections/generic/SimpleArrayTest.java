package ru.job4j.collections.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    SimpleArray<Integer> array;

    @Before
    public void setUp() throws Exception {
        this.array = new SimpleArray<>(5);
        this.array.add(1);
        this.array.add(2);
        this.array.add(3);
        this.array.add(4);
        this.array.add(5);
    }

    @Test
    public void whenGetSimpleArray5Numbers() {
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
        assertThat(array.get(3), is(4));
        assertThat(array.get(4), is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetWithArrayIndexOutOfBoundsException() {
        array.get(6);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void whenCreatingSimpleArrayWithSizeLess0() {
        SimpleArray<Integer> arrs = new SimpleArray<>(-1);
    }

    @Test
    public void whenSetSimpleArrayCellWithIndex2() {
        int result = array.set(2, 666);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(result, is(array.get(2)));
        assertThat(array.get(3), is(4));
        assertThat(array.get(4), is(5));
    }

    @Test
    public void whenDeleteSimpleArrayItemWithIndex2() {
        array.delete(2);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(null, is(array.get(2)));
        assertThat(array.get(3), is(4));
        assertThat(array.get(4), is(5));
    }

    @Test
    public void whenIterateSimpleArray() {
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().next(), is(2));
        assertThat(array.iterator().next(), is(3));
        assertThat(array.iterator().next(), is(4));
        assertThat(array.iterator().next(), is(5));
        assertThat(array.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateSimpleArrayWithNoSuchElementException() {
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(2));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(3));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(4));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(5));
        assertThat(array.iterator().hasNext(), is(false));
        array.iterator().next();
    }
}