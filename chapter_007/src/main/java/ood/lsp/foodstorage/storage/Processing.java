package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Processing extends StorageDecorator {
    private List<Product> recycleProducts;
    private String name;
    private int size;

    public Processing(Trash storage) {
        super(storage);
        this.recycleProducts = new ArrayList<>();
        this.name = super.getName();
        this.size = super.getSize();
    }


    @Override
    public boolean isAppropriate(Product product) {
        boolean result = false;
        if (super.isAppropriate(product) && product.isRecyclable()) {
            result = true;
        }
        return result;
    }


    @Override
    public List<Product> getAllProductsInStorage() {
        return this.recycleProducts;
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
