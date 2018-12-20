package ru.job4j.collections.list;

public class Node<E> {

    final E value;

    private Node<E> next;

    public Node(E value) {
        this.value = value;
    }

    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node one = first;
        Node two = first;
        while (one != null && two != null) {
            one = one.next;
            two = two.next.next;
            if (one == two) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
