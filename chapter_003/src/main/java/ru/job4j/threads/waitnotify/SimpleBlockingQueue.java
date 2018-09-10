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

}
