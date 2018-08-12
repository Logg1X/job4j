package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LinkedListContainerTest {

    private LinkedListContainer<Integer> container;

    @Before
    public void setUp() throws Exception {
        container = new LinkedListContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
    }

    @Test
    public void whenGetAllElementsIntoContainer() {
        assertThat(container.get(0), is(5));
        assertThat(container.get(1), is(4));
        assertThat(container.get(2), is(3));
        assertThat(container.get(3), is(2));
        assertThat(container.get(4), is(1));
    }

    @Test
    public void whenAddAnotherOneElementThanSizeIs6() {
        container.add(6);
        assertThat(container.getSize(), is(6));
    }

    @Test
    public void whenAddAnotherOneElementInTheAndContainer() {
        container.addLast(7);
        assertThat(container.get(6), is(7));
    }

    @Test
    public void whenIterateContainerThroughForEch() {
        AtomicInteger iteration = new AtomicInteger(5);
        container.forEach(integer -> assertThat(integer, is(iteration.getAndDecrement())));
    }

    @Test
    public void whenIterateContainerThroughIterator() {
        Iterator<Integer> iterator = container.iterator();
        Iterator<Integer> iterator2 = container.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator2.hasNext(), is(true));
        assertThat(iterator2.next(), is(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateContainerThroughForEchWithConcurrentModificationException() {
        container.forEach(i -> container.add(i));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateContainerThroughIteratorWithNoSuchElementException() {
        LinkedListContainer<Integer> cont = new LinkedListContainer<>();
        Iterator<Integer> it = cont.iterator();
        cont.add(1);
        it.next();
        assertThat(container.get(0), is(1));
        it.next();
    }

    @Test
    public void whenAddAnotherOneElementInTheAndEmptyContainer() {
        LinkedListContainer<Integer> cont = new LinkedListContainer<>();
        cont.addLast(1);
        assertThat(cont.get(0), is(1));
    }

}