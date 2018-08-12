package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void setUp() throws Exception {
        this.stack = new SimpleStack<>();
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        this.stack.push(4);
    }

    @Test
    public void whenPushAnotherOneElement() {
        this.stack.push(5);
        assertThat(stack.getSize(), is(5));
    }

    @Test
    public void whenPollOneElement() {
        assertThat(this.stack.poll(), is(4));
        assertThat(stack.getSize(), is(3));
    }

    @Test
    public void whenPollAllElements() {
        assertThat(this.stack.poll(), is(4));
        assertThat(this.stack.poll(), is(3));
        assertThat(this.stack.poll(), is(2));
        assertThat(this.stack.poll(), is(1));
        assertThat(stack.getSize(), is(0));
    }
}