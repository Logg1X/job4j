package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.List;

public abstract class StorageDecorator implements Storage {
    private final Storage storage;
    protected int currentConteiner;

    public StorageDecorator(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean isAppropriate(Product product) {
        return storage.isAppropriate(product);
    }

    @Override
    public List<Product> getAllProductsInStorage() {
        return storage.getAllProductsInStorage();
    }

    @Override
    public String getName() {
        return storage.getName();
    }

    @Override
    public int getSize() {
        return storage.getSize();
    }
}
