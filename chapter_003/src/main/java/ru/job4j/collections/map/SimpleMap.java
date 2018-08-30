package ru.job4j.collections.map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Node> {
    private Node[] map;
    private int modCount;
    private int size;

    public SimpleMap(int size) {
        this.map = new Node[size];
        this.modCount = 0;
        this.size = size;
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int position = getPosition(key);
        if (position >= size) {
            size += position;
            Node[] newArray = new Node[size];
            System.arraycopy(map, 0, newArray, 0, map.length);
            map = newArray;
        }
        if (map[position] == null) {
            map[position] = new Node<>(key, value);
            this.modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        Node<K, V> node = this.map[getPosition(key)];
        V result = null;
        if (node != null && node.key.hashCode() == key.hashCode() && node.key.equals(key)) {
            result = node.value;
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int position = getPosition(key);
        if (map[position] != null
                && map[position].key.hashCode() == key.hashCode()
                && map[position].key.equals(key)) {
            map[position] = null;
            result = true;
            modCount++;
        }
        return result;
    }

    private int getPosition(K key) {
        return Math.abs((31 * key.hashCode() % 10)) * 2 + 5;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                while (this.cursor < map.length) {
                    if (map[cursor] == null) {
                        cursor++;
                        continue;
                    }
                    if (map[cursor].getClass().equals(Node.class)) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Node next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("NoSuchElementException");
                }
                return map[this.cursor++];
            }
        };
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " " + value;
        }
    }
}
