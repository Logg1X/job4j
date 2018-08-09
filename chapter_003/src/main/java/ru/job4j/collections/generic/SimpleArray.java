package ru.job4j.collections.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int fullSize;
    private int position = 0;
    private int iterPosition = 0;

    public SimpleArray(final int size) {
        this.array = new Object[size];
        this.fullSize = size;
    }

    public boolean add(T model) {
        if (position > fullSize) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException: " + position);
        }
        array[position++] = model;
        return true;
    }

    public T set(int index, T model) {
        array[index] = model;
        return model;
    }

    public T delete(int index) {
        T model = (T) array[index];
        array[index] = null;
        return model;
    }

    public T get(int index) {
        return (T) array[index];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterPosition != fullSize && iterPosition < fullSize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("NoSuchElementException");
                }
                return (T) array[iterPosition++];
            }
        };
    }
}
