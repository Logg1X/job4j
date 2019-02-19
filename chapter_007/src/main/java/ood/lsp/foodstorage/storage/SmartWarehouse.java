package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

public class SmartWarehouse extends StorageDecorator {
    public SmartWarehouse(Warehouse storage) {
        super(storage);
    }

    @Override
    public boolean isAppropriate(Product product) {
        return super.isAppropriate(product) && !product.isPerishable();
    }
}
