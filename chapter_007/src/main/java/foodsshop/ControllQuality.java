package foodsshop;

import foodsshop.storage.Shop;
import foodsshop.storage.Trash;
import foodsshop.storage.Warehouse;

import java.util.Collection;

public class ControllQuality {

    void sortProductsByStorage(Collection<Product> products) {
        for (Product product : products) {
            double unsuitabilityLevel = product.getUnsuitabilityLevel();
            if (unsuitabilityLevel > 25 && unsuitabilityLevel < 100) {
                product.setStorage(new Shop());
                product.sendToStorage();
                if (unsuitabilityLevel > 75) {
                    product.setDiscount(35);
                }
            } else if (unsuitabilityLevel < 25.0) {
                product.setStorage(new Warehouse());
                product.sendToStorage();
            } else if (unsuitabilityLevel > 100.0) {
                product.setStorage(new Trash());
                product.sendToStorage();
            }
        }
    }
}
