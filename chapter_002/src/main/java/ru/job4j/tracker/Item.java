package ru.job4j.tracker;

public class Item {
    private String id;
    private String name;
    private String descripton;
    private long crate;
    private String[] comments;

    public Item(String name, String descripton) {
        this.name = name;
        this.descripton = descripton;
    }

    public Item(String name, String descripton, long crate) {
        this.name = name;
        this.descripton = descripton;
        this.crate = crate;
    }

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescripton() {
        return descripton;
    }

    public Item setDescripton(String descripton) {
        this.descripton = descripton;
        return this;
    }

    @Override
    public String toString() {
        return "________________________________________________ " + System.lineSeparator()
                + "Задача №: " + id + "." + System.lineSeparator()
                + "Имя: " + name + "." + System.lineSeparator()
                + "Описание: " + descripton + "." + System.lineSeparator()
                + "________________________________________________";
    }
}
