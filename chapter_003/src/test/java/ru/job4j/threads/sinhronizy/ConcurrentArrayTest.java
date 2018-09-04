package ru.job4j.threads.sinhronizy;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConcurrentArrayTest {

    private ConcurrentArray<Integer> array;

    @Before
    public void setUp() throws Exception {
        array = new ConcurrentArray<>();
    }

    @Test
    public void whenIterateAndAddElements() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                array.add(i);
            }
        });
        threadA.start();
        threadA.join();
        Thread threadB = new Thread(() -> {
            Iterator iter1 = array.iterator();
            iter1.hasNext();
            array.add(111);
            array.add(1);
            array.add(451);
            for (int i = 0; i < 5; i++) {
                assertThat(iter1.next(), is(i));
            }
            });
        threadB.start();
        threadB.join();
    }

    @Test
    public void whenGetElements() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                array.add(i);
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                assertThat(array.get(i), is(i));
            }
        });
        threadA.start();
        threadA.join();
        threadB.start();
        assertThat(array.getSize(), is(10));
        threadB.join();
    }
}
