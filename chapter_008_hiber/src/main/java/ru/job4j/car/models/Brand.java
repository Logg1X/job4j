package ru.job4j.car.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Brand {
    private int id;
    private String iname;

    public Brand(int id, String iname) {
        this.id = id;
        this.iname = iname;
    }

    public Brand(int id) {
        this.id = id;
    }

    public Brand() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "iname")
    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return id == brand.id
                && Objects.equals(iname, brand.iname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iname);
    }

    @Override
    public String toString() {
        return "Brand{"
                + "id=" + id
                + ", iname='" + iname + '\''
                + '}';
    }
}
