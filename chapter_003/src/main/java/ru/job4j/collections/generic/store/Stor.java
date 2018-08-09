package ru.job4j.collections.generic.store;

import ru.job4j.collections.generic.store.Base;

public interface Stor<T extends Base> {

    boolean add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
