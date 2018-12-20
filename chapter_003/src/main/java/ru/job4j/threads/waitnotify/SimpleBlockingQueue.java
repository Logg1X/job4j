package ru.job4j.threads.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final int maxSize;
    private final Queue<T> queue;

    public SimpleBlockingQueue(final int size) {
        this.maxSize = size;
        queue = new LinkedList<>();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
        );
        consumer.start();
        Thread prod = new Thread(
                () -> {
                    for (int index = 0; index != 10; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
        prod.start();
        prod.join();
        if (prod.getState().equals(Thread.State.TERMINATED)) {
            consumer.interrupt();
        }
    }

    public synchronized void offer(final T value) throws InterruptedException {
        while (this.queue.size() >= this.maxSize) {
            wait();
        }
        notify();
        queue.offer(value);
    }

    public synchronized final T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        notify();
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

