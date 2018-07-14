package ru.job4j.collections;

import org.junit.Test;
import ru.job4j.collections.models.Task;
import ru.job4j.collections.search.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("middle", 8));
        queue.put(new Task("middle", 9));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
