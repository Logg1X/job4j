package ru.job4j.threads.sinhronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collections.list.DynamicArray;

import java.util.Iterator;

@ThreadSafe
public class ConcurrentArray<T> implements Iterable {
    @GuardedBy("this")
    private final DynamicArray<T> array;

    public ConcurrentArray() {
        array = new DynamicArray<>();
    }

    public synchronized T get(int index) {
        return array.get(index);
    }

    public synchronized int getSize() {
        return array.getSize();
    }

    public synchronized boolean add(T value) {
        return array.add(value);
    }


    private DynamicArray<T> copy(DynamicArray<T> array) {
        DynamicArray clone = new DynamicArray(array.getSize());
        array.forEach(clone::add);
        return clone;
    }

    @Override
    public synchronized Iterator iterator() {
        return this.copy(this.array).iterator();
    }
}
