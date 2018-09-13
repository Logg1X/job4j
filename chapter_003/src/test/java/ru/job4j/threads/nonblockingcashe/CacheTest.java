package ru.job4j.threads.nonblockingcashe;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheTest {

    private Cache cache;
    private Cache.Base model;


    @Before

    public void setUp() throws Exception {
        this.cache = new Cache();
        this.model = new Cache.Base(1, "pasha");
        cache.add(model);
    }

    @Test
    public void whenUpdateWithTwoThreads() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(() -> IntStream.range(0, 55).forEach(
                value -> {
                    try {
                        cache.update(new Cache.Base(1, "pasha"));
                    } catch (RuntimeException oe) {
                        ex.set(oe);

                    }
                }));
        thread1.start();
        thread1.join();
        assertThat(ex.get().getMessage(), is("В изменении отказанно!"));
    }
}