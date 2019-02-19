package ood.lsp.foodstorage.storage;

import ood.lsp.foodstorage.products.Product;

public class SmartTrash extends StorageDecorator {

    public SmartTrash(Trash storage) {
        super(storage);
    }

    @Override
    public boolean isAppropriate(Product product) {
        return super.isAppropriate(product) && !product.isRecyclable();
    }
}
