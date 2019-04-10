package ru.toppavel.models;

import java.util.Objects;


public class Wd {
    private int id;
    private String typeName;

    public Wd() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wd wd = (Wd) o;
        return id == wd.id
                && Objects.equals(typeName, wd.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }

    @Override
    public String toString() {
        return "Wd{"
                + "id=" + id
                + ", typeName='" + typeName + '\''
                + '}';
    }
}
