package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.List;

public interface Storage {
    default void store(Product product) {
        if (this.getAllProductsInStorage().size() == this.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Storage is full!");
        }
        if (isAppropriate(product)) {
            this.getAllProductsInStorage().add(product);
        }
    }

    boolean isAppropriate(Product product);

    List<Product> getAllProductsInStorage();

    String getName();

    int getSize();
}
