package foodsshop;

import foodsshop.storage.Storage;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food implements Product {
    private String name;
    private Storage storage;
    private LocalDate expaireDate;
    private LocalDate createDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate expaireDate, LocalDate createDate, double price, int discount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public void sendToStorage() {
        this.storage.store(this);
    }

    @Override
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalDate getExpaireDate() {
        return expaireDate;
    }

    @Override
    public void setExpaireDate(LocalDate expaireDate) {
        this.expaireDate = expaireDate;
    }

    @Override
    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
        this.price = price - (price / 100 * discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && discount == food.discount
                && Objects.equals(name, food.name)
                && Objects.equals(storage, food.storage)
                && Objects.equals(expaireDate, food.expaireDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storage, expaireDate, createDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", storage=" + storage
                + ", expaireDate=" + expaireDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
