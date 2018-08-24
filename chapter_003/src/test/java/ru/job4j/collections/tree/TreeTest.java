package ru.job4j.collections.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TreeTest {
    Tree<Integer> tree;

    @Before
    public void prepare() {
        tree = new Tree<>(1);
        tree.add(1, 2);
    }


    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertTrue(tree.findBy(6).isPresent());
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertFalse(tree.add(1, 2));
        assertFalse(tree.findBy(7).isPresent());
    }

    @Test
    public void whenIterateThenTrue() {
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        Iterator itr = tree.iterator();
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(1));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(2));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(3));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(4));
        assertTrue(itr.hasNext());
        assertThat(itr.next(), is(5));
        assertFalse(itr.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThenException() {
        Iterator itr = tree.iterator();
        tree.add(2, 3);
        itr.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateAndOutOfBoundsThenException() {
        Iterator itr = tree.iterator();
        itr.next();
        itr.next();
        itr.next();
    }
}