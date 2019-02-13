package ood.lsp.foodstorage;

import ood.lsp.foodstorage.products.Product;
import ood.lsp.foodstorage.storage.Storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllQuality {

    private Map<String, Storage> allStorage = new HashMap<>();

    public void addStorage(String name, Storage storage) {
        this.allStorage.put(name, storage);
    }

    public Map<String, Storage> getAllStorage() {
        return allStorage;
    }


    boolean sortProductsByStorage(List<Product> products) {
        boolean result = false;
        for (Storage storage : allStorage.values()) {
            for (Product product : products) {
                result = storage.store(product);
            }
        }
        return result;

//        for (Product product : products) {
//            double unsuitabilityLevel = product.getUnsuitabilityLevel();
//            if (unsuitabilityLevel > 25 && unsuitabilityLevel < 100) {
//                product.setStorage(new Shop());
//                product.sendToStorage();
//                if (unsuitabilityLevel > 75) {
//                    product.setDiscount(35);
//                }
//            } else if (unsuitabilityLevel < 25.0) {
//                product.setStorage(new Warehouse());
//                product.sendToStorage();
//            } else if (unsuitabilityLevel > 100.0) {
//                product.setStorage(new Trash());
//                product.sendToStorage();
//            }
//        }
    }
}
