package foodsshop.storage;

import foodsshop.Product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private int size;
    private String name;
    private int currentConteiner;
    private List<Product> products;

    public Warehouse(String name, int size) {
        this.size = size;
        this.name = name;
        this.products = new ArrayList<>();
        this.currentConteiner = 0;
    }

    public List<Product> getAllProductsInStorage() {
        return products;
    }

    @Override
    public boolean store(Product product) {
        if (this.currentConteiner == size) {
            throw new ArrayIndexOutOfBoundsException("Storage is full!");
        }
        boolean result = false;
        double unsuitabilityLevel = product.getUnsuitabilityLevel();
        if (unsuitabilityLevel < 25.0) {
            result = this.products.add(product);
        }
        return result;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
