package ood.lsp.foodstorage;

import ood.lsp.foodstorage.products.Product;
import ood.lsp.foodstorage.storage.Storage;

import java.util.ArrayList;
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

    void sortProductsByStorage(List<Product> products) {
        for (Storage storage : allStorage.values()) {
            for (Product product : products) {
                storage.store(product);
            }
        }
    }

    public void resort() {
        List<Product> prodForResort = this.getAllProductsInAllStorage();
        this.clearAllStorage();
        this.sortProductsByStorage(prodForResort);
    }

    private void clearAllStorage() {
        allStorage.values().stream().forEach(storage -> storage.getAllProductsInStorage().clear());
    }

    private List<Product> getAllProductsInAllStorage() {
        List<Product> prodForResort = new ArrayList<>();
        allStorage.values()
                .forEach(storage ->
                        prodForResort.addAll(storage.getAllProductsInStorage()));
        return prodForResort;
    }
}

