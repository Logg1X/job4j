package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.List;

public interface Storage {
    void store(Product product);

    boolean isAppropriate(Product product);

    List<Product> getAllProductsInStorage();

    String getName();

    int getSize();
}
