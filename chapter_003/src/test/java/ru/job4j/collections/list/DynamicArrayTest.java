package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayTest {
    private DynamicArray<Integer> arra;
    private DynamicArray<Integer> arraSizef = new DynamicArray<>(5);

    @Before
    public void setUp() throws Exception {
        arra = new DynamicArray<>();
        arra.add(1);
        arra.add(2);
        arra.add(3);
        arra.add(4);
        arra.add(5);
        arra.add(6);
        arra.add(7);
        arra.add(8);
        arra.add(9);
        arra.add(10);
        arraSizef.add(1);
        arraSizef.add(2);
        arraSizef.add(3);
        arraSizef.add(4);
        arraSizef.add(5);
    }

    @Test
    public void whenAddElementMoreThenArraySize() {
        arra.add(11);
        assertThat(arra.getFulSize(), is(15));
        arraSizef.add(6);
        assertThat(arraSizef.getFulSize(), is(7));
    }

    @Test
    public void whenIterateArray() {
        Iterator<Integer> iterator = arraSizef.iterator();
        Iterator<Integer> iterator2 = arraSizef.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator2.next(), is(1));
    }

    @Test
    public void whenIterateArrayThroughForEch() {
        AtomicInteger count = new AtomicInteger(1);
        arra.forEach(integer -> assertThat(integer, is(count.getAndIncrement())));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateArrayThroughForEchWithException() {
        arra.forEach(integer -> arra.add(55));
    }

    @Test
    public void whenGetAllElements() {
        assertThat(arra.get(0), is(1));
        assertThat(arra.get(1), is(2));
        assertThat(arra.get(2), is(3));
        assertThat(arra.get(3), is(4));
        assertThat(arra.get(4), is(5));
        assertThat(arra.get(5), is(6));
        assertThat(arra.get(6), is(7));
        assertThat(arra.get(7), is(8));
        assertThat(arra.get(8), is(9));
        assertThat(arra.get(9), is(10));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateWithNoSuchElementException() {
        DynamicArray<Integer> art = new DynamicArray<>(0);
        Iterator<Integer> iterator = art.iterator();
        iterator.next();
    }


}