package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private int size;
    private String name;
    private List<Product> products;

    public Warehouse(String name, int size) {
        this.size = size;
        this.name = name;
        this.products = new ArrayList<>();
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
    public boolean isAppropriate(Product product) {
        boolean result = false;
        double unsuitabilityLevel = product.getUnsuitabilityLevel();
        if (unsuitabilityLevel < 25.0) {
            result = true;
        }
        return result;
    }
}
