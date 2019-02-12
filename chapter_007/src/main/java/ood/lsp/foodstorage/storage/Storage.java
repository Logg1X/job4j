package foodsshop.storage;

import foodsshop.Product;

import java.util.List;

public interface Storage {
    boolean store(Product product);

    List<Product> getAllProductsInStorage();

    String getName();

    int getSize();
}
