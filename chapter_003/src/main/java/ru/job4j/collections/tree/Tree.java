package ru.job4j.collections.tree;

import java.util.*;

public class Tree<E extends Comparable<E>>
        implements SimpleTree<E>, Iterable<E> {

    private Node<E> root;
    private int modCount;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;

        if (!findBy(child).isPresent()) {
            Optional<Node<E>> e = this.findBy(parent);
            result = e.isPresent();
            if (result) {
                e.get().add(new Node<>(child));
                modCount++;
            }
        }
        return result;
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int expectedModCount = modCount;
        private Queue<Node<E>> data = new LinkedList<>();

        public Itr() {
            this.data.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !data.isEmpty();
        }

        @Override
        public E next() {
            Node<E> result;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException("NoSuchElementException");
            }
            result = data.poll();
            for (Node<E> child : result.leaves()) {
                data.offer(child);
            }
            return result.getValue();
        }
    }

}
