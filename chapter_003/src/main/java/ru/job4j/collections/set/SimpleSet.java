package ru.job4j.collections.set;

import ru.job4j.collections.generic.SimpleArray;

import java.util.Set;

public class SimpleSet<T> extends SimpleArray<T> {



    public SimpleSet() {
        super(100);
    }

    @Override
    public boolean add(T model) {
        return !contains(model) ? super.add(model) : false;
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
}
