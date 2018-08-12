package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<T> implements Iterable<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;
    private int modCount;


    public LinkedListContainer() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(null, value, f);
        this.first = newNode;
        if (f == null) {
            this.last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    public void addLast(T value) {
        final Node<T> lst = last;
        final Node<T> newNode = new Node<>(lst, value, null);
        last = newNode;
        if (lst == null) {
            first = newNode;
        } else {
            lst.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int position) {
        Node<T> result;
        if (size / 2 > position) {
            result = this.first;
            for (int i = 0; i < position; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = size - 1; i > position; i--) {
                result = result.prev;
            }
        }
        return result.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> rt = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return rt != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<T> result = rt;
                rt = rt.next;
                return result.item;
            }
        };
    }

    private class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

