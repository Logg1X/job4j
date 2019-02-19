package ood.lsp.foodstorage.products;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Product {
    default double getUnsuitabilityLevel() {
        var a = (double) DAYS.between(getCreateDate(), getExpaireDate());
        var b = (double) DAYS.between(getCreateDate(), LocalDate.now());
        var result = b / a * 100;
        return result;
    }

    default boolean isRecyclable() {
        return false;
    }

    default boolean isPerishable() {
        return false;
    }

    String getName();

    void setName(String name);

    LocalDate getExpaireDate();

    void setExpaireDate(LocalDate expaireDate);

    LocalDate getCreateDate();

    void setCreateDate(LocalDate createDate);

    double getPrice();

    void setPrice(double price);

    int getDiscount();

    void setDiscount(int discount);
}
