package foodsshop.storage;

import foodsshop.Product;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    @Override
    public void store(Product product) {
        products.add(product);
    }
}
