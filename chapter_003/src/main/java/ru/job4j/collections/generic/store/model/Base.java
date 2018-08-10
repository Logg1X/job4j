package ru.job4j.collections.generic.store.model;

public abstract class Base {

    private final String id;

    protected Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
