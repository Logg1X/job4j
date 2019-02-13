package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.List;

public interface Storage {
    boolean store(Product product);

    List<Product> getAllProductsInStorage();

    String getName();

    int getSize();
}
