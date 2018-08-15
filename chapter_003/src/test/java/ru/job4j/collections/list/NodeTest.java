package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NodeTest {

    private Node<Integer> first;
    private Node<Integer> second;
    private Node<Integer> third;
    private Node<Integer> four;

    @Before
    public void createNode() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        four = new Node<>(4);
    }

    @Test
    public void whenNodeCycleOnTheFourthElement() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(four);
        four.setNext(first);
        assertTrue(Node.hasCycle(first));
    }

    @Test
    public void whenNodeCycle2ItemsInARow() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(third);
        four.setNext(null);
        assertTrue(Node.hasCycle(first));
    }

    @Test
    public void whenNodeNoCycle() {
        first.setNext(second);
        second.setNext(third);
        third.setNext(four);
        four.setNext(null);
        assertFalse(Node.hasCycle(first));
    }
}