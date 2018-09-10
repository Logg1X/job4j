package ru.job4j.threads.waitnotify;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void whenTwoTreadProducerAndCustomer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        int iteration = 10;
        Thread producer = new Thread(
                () -> {
            for (int i = 0; i < iteration; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(
                () -> {
            for (int i = 0; i < iteration; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}