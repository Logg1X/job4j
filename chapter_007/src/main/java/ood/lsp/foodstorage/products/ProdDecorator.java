package ood.lsp.foodstorage.products;

import java.time.LocalDate;

public abstract class ProdDecorator implements Product {
    protected Product product;

    public ProdDecorator(Product product) {
        this.product = product;
    }

    @Override
    public double getUnsuitabilityLevel() {
        return product.getUnsuitabilityLevel();
    }


    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public void setName(String name) {
        product.setName(name);
    }

    @Override
    public LocalDate getExpaireDate() {
        return product.getExpaireDate();
    }

    @Override
    public void setExpaireDate(LocalDate expaireDate) {
        product.setExpaireDate(expaireDate);
    }

    @Override
    public LocalDate getCreateDate() {
        return product.getCreateDate();
    }

    @Override
    public void setCreateDate(LocalDate createDate) {
        product.setCreateDate(createDate);
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public void setPrice(double price) {
        product.setPrice(price);
    }

    @Override
    public double getDiscount() {
        return product.getDiscount();
    }

    @Override
    public void setDiscount(int discount) {
        product.setDiscount(discount);
    }
}
