package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements Iterable<E> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private int position;
    private Object[] array;
    private int modCount;

    public DynamicArray(int size) {
        this.array = new Object[size];
        this.size = size;
        this.position = 0;
    }

    public DynamicArray() {
        this.array = new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        this.position = 0;
    }

    public E get(int index) {
        return (E) array[index];
    }

    public int getSize() {
        return size;
    }

    public boolean add(E value) {
        if (position >= size) {
            size *= 1.5;
            Object[] newArray = new Object[size];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[position++] = value;
        modCount++;
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                return (E) array[cursor++];
            }
        };
    }
}
