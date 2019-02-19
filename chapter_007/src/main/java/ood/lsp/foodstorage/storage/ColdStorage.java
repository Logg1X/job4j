package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

public class ColdStorage extends StorageDecorator {

    public ColdStorage(Warehouse storage) {
        super(storage);
    }

    @Override
    public boolean isAppropriate(Product product) {
        return super.isAppropriate(product) && product.isPerishable();
    }
}
