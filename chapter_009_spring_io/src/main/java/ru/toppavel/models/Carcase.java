package ru.toppavel.models;

import java.util.Objects;


public class Carcase {
    private int id;
    private String typeName;

    public Carcase() {
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
        Carcase carcase = (Carcase) o;
        return id == carcase.id
                && Objects.equals(typeName, carcase.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }

    @Override
    public String toString() {
        return "Carcase{"
                + "id=" + id
                + ", typeName='" + typeName + '\''
                + '}';
    }
}


