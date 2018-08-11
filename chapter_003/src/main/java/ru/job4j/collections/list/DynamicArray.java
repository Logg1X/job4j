package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements Iterable<E> {
    private static final int DEFAULT_SIZE = 10;
    private int fulSize;
    private int position;
    private Object[] array;
    private int modCount;

    public int getFulSize() {
        return fulSize;
    }

    public DynamicArray(int size) {
        this.array = new Object[size];
        this.fulSize = size;
        this.position = 0;
    }

    public DynamicArray() {
        this.array = new Object[DEFAULT_SIZE];
        this.fulSize = DEFAULT_SIZE;
        this.position = 0;
    }

    public boolean add(E value) {
        if (position >= fulSize) {
//            Object[] newArray = Arrays.copyOf(array, (fulSize *= 1.5));
            fulSize *= 1.5;
            Object[] newArray = new Object[fulSize];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[position++] = value;
        modCount++;
        return true;
    }

    public E get(int index) {
        return (E) array[index];
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < fulSize;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {

                    throw new ConcurrentModificationException();
                }
                if (cursor >= fulSize) {
                    throw new NoSuchElementException();
                }
                return (E) array[cursor++];
            }
        };
    }
}
