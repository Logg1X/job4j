package ru.job4j.collections.list;

public class SimpleQueue<T> extends LinkedListContainer<T> {

    public SimpleQueue() {
        super();
    }

    public T poll() {
        return super.deleteLast();
    }

    public void push(T value) {
        super.add(value);
    }
}
