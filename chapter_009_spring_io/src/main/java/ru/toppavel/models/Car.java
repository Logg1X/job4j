package ru.toppavel.models;

import java.io.Serializable;
import java.util.Objects;


public class Car {
    private int id;
    private String name;
    private Brand brandByIdBrand;
    private Carcase carcaseByIdCarcase;
    private Color colorByIdColor;
    private Wd wdByIdWd;

    public Car(String name, Brand brandByIdBrand, Carcase carcaseByIdCarcase, Color colorByIdColor, Wd wdByIdWd) {
        this.name = name;
        this.brandByIdBrand = brandByIdBrand;
        this.carcaseByIdCarcase = carcaseByIdCarcase;
        this.colorByIdColor = colorByIdColor;
        this.wdByIdWd = wdByIdWd;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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


    public Brand getBrandByIdBrand() {
        return brandByIdBrand;
    }

    public void setBrandByIdBrand(Brand brandByIdBrand) {
        this.brandByIdBrand = brandByIdBrand;
    }


    public Carcase getCarcaseByIdCarcase() {
        return carcaseByIdCarcase;
    }

    public void setCarcaseByIdCarcase(Carcase carcaseByIdCarcase) {
        this.carcaseByIdCarcase = carcaseByIdCarcase;
    }


    public Color getColorByIdColor() {
        return colorByIdColor;
    }

    public void setColorByIdColor(Color colorByIdColor) {
        this.colorByIdColor = colorByIdColor;
    }

    public Wd getWdByIdWd() {
        return wdByIdWd;
    }

    public void setWdByIdWd(Wd wdByIdWd) {
        this.wdByIdWd = wdByIdWd;
    }
}
