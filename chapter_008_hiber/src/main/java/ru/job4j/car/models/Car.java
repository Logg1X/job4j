package ru.job4j.car.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Car implements Serializable {
    private int id;
    private String name;
    private Brand brandByIdBrand;
    private Carcase carcaseByIdCarcase;
    private Color colorByIdColor;
    private Wd wdByIdWd;

    public Car(int id, String name, Brand brandByIdBrand, Carcase carcaseByIdCarcase, Color colorByIdColor, Wd wdByIdWd) {
        this.id = id;
        this.name = name;
        this.brandByIdBrand = brandByIdBrand;
        this.carcaseByIdCarcase = carcaseByIdCarcase;
        this.colorByIdColor = colorByIdColor;
        this.wdByIdWd = wdByIdWd;
    }

    public Car(int id) {
        this.id = id;
    }

    public Car(String name, Brand brandByIdBrand, Carcase carcaseByIdCarcase, Color colorByIdColor, Wd wdByIdWd) {
        this.name = name;
        this.brandByIdBrand = brandByIdBrand;
        this.carcaseByIdCarcase = carcaseByIdCarcase;
        this.colorByIdColor = colorByIdColor;
        this.wdByIdWd = wdByIdWd;
    }

    public Car() {
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id", nullable = false)
    public Brand getBrandByIdBrand() {
        return brandByIdBrand;
    }

    public void setBrandByIdBrand(Brand brandByIdBrand) {
        this.brandByIdBrand = brandByIdBrand;
    }

    @ManyToOne
    @JoinColumn(name = "id_carcase", referencedColumnName = "id", nullable = false)
    public Carcase getCarcaseByIdCarcase() {
        return carcaseByIdCarcase;
    }

    public void setCarcaseByIdCarcase(Carcase carcaseByIdCarcase) {
        this.carcaseByIdCarcase = carcaseByIdCarcase;
    }

    @ManyToOne
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    public Color getColorByIdColor() {
        return colorByIdColor;
    }

    public void setColorByIdColor(Color colorByIdColor) {
        this.colorByIdColor = colorByIdColor;
    }

    @ManyToOne
    @JoinColumn(name = "id_wd", referencedColumnName = "id")
    public Wd getWdByIdWd() {
        return wdByIdWd;
    }

    public void setWdByIdWd(Wd wdByIdWd) {
        this.wdByIdWd = wdByIdWd;
    }
}
