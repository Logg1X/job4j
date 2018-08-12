package ru.job4j.collections.list;

public class SimpleStack<T> extends LinkedListContainer<T> {

    public SimpleStack() {
        super();
    }

    public T poll() {
        return super.delete();
    }

    public void push(T value) {
        super.add(value);
    }
}
