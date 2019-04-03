package ru.job4j.car.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Carcase {
    private int id;
    private String typeName;

    public Carcase(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Carcase(int id) {
        this.id = id;
    }

    public Carcase() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name")
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


