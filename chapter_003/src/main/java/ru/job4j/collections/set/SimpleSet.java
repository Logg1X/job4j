package ru.job4j.collections.set;

import ru.job4j.collections.generic.SimpleArray;
import ru.job4j.collections.list.DynamicArray;

import java.util.Iterator;
import java.util.Set;

public class SimpleSet<T> implements Iterable<T> {

    private DynamicArray<T> array;


    public SimpleSet(int size) {
        array = new DynamicArray<>(size);

    }

    public boolean add(T model) {
        return !contains(model) ? array.add(model) : false;
    }

    public boolean contains(T a) {
        boolean result = false;
            for (T t : this) {
                if (t == null) {
                    continue;
                } else if (t.equals(a)) {
                    result = true;
                    break;
                }
            }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
