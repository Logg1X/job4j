package ru.job4j.threads.threadpool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.threads.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@ThreadSafe
public class ThreadPool {
@GuardedBy("this")
    private final List<MyThread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);
    private int size;
    public ThreadPool() {
        this.size = Runtime.getRuntime().availableProcessors();
        IntStream.range(0, size).forEach(
                i -> threads.add(new MyThread())
        );
        threads.forEach(MyThread::run);
    }

    public void work(Runnable job) throws InterruptedException {
        synchronized (this.tasks) {
            this.tasks.offer(job);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        threads.forEach(MyThread::interrupt);
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            synchronized (tasks) {
                while (!Thread.currentThread().isInterrupted()) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
