package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
    }

    @Test
    public void whenPushThroughOneElement() {
        assertThat(queue.getSize(), is(4));
        queue.push(5);
        assertThat(queue.getSize(), is(5));
    }

    @Test
    public void whenPollAllElements() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertThat(queue.getSize(), is(0));
    }
}