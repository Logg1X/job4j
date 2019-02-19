package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private int size;
    private String name;
    private List<Product> products;

    public Shop(String name, int size) {
        this.size = size;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public List<Product> getAllProductsInStorage() {
        return products;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }


    @Override
    public boolean isAppropriate(Product product) {
        boolean result = false;
        double unsuitabilityLevel = product.getUnsuitabilityLevel();
        if (unsuitabilityLevel > 25 && unsuitabilityLevel < 100) {
            result = true;
            if (unsuitabilityLevel > 75) {
                product.setDiscount(35);
            }
        }
        return result;
    }
}
