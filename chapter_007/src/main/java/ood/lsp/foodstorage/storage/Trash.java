package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private int size;
    private String name;
    private int currentConteiner;
    private List<Product> products;

    public Trash(String name, int size) {
        this.size = size;
        this.name = name;
        this.products = new ArrayList<>();
        this.currentConteiner = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public List<Product> getAllProductsInStorage() {
        return products;
    }

    @Override
    public void store(Product product) {
        if (this.currentConteiner == size) {
            throw new ArrayIndexOutOfBoundsException("Storage is full!");
        }
        if (isAppropriate(product)) {
            this.products.add(product);
            this.currentConteiner++;
        }

    }

    @Override
    public boolean isAppropriate(Product product) {
        boolean result = false;
        double unsuitabilityLevel = product.getUnsuitabilityLevel();
        if (unsuitabilityLevel > 100.0) {
            result = true;
        }
        return result;
    }
}
