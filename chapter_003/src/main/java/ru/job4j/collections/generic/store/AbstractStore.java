package ru.job4j.collections.generic.store;

import ru.job4j.collections.generic.SimpleArray;
import ru.job4j.collections.generic.store.model.Base;

class RoleStore<Role extends Base> extends AbstractStore {

    protected RoleStore(SimpleArray<Role> array) {
        super(array);
    }
}

class UserStore<User extends Base> extends AbstractStore<User> {

    protected UserStore(SimpleArray<User> array) {
        super(array);
    }
}

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private final SimpleArray<T> array;

    protected AbstractStore(SimpleArray<T> array) {
        this.array = array;
    }

    @Override
    public boolean add(T model) {
        array.add(model);
        return true;
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index == -1) {
            throw new RuntimeException("ID: " + id + " не найден");
        }
        array.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int in = getIndex(id);
        if (in == -1) {
            throw new RuntimeException("ID: " + id + " не найден");
        }
        array.delete(in);
        return true;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T base : array) {
            if (base.getId().equals(id)) {
                result = base;
                break;
            }
        }
        return result;
    }

    private int getIndex(String id) {
        int index = -1;
        for (int i = 0; i < array.getFullSize(); i++) {
            if (array.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
