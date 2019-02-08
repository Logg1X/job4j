package foodsshop;

import foodsshop.storage.Shop;
import foodsshop.storage.Trash;
import foodsshop.storage.Warehouse;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControllQualityTest {


    @Test
    public void whenSortProductByStorageThenShopTrashWarehouse() {
        ControllQuality controllQuality = new ControllQuality();
        List<Product> products = List.of(
                new Eat("Eat1",
                        LocalDate.of(2019, 02, 20),
                        LocalDate.of(2019, 01, 01),
                        19.0,
                        0),
                new Eat("Eat2",
                        LocalDate.of(2019, 02, 28),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0),
                new Eat("Eat3",
                        LocalDate.of(2019, 02, 9),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0),
                new Eat("Eat4",
                        LocalDate.of(2019, 03, 28),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0));
        controllQuality.sortProductsByStorage(products);
        assertThat(Shop.getProducts().size(), is(2));
        assertTrue(Shop.getProducts().contains(products.get(1)));
        assertTrue(Shop.getProducts().contains(products.get(0)));
        assertThat(Trash.getProducts().size(), is(1));
        assertTrue(Trash.getProducts().contains(products.get(2)));
        assertThat(Warehouse.getProducts().size(), is(1));
        assertTrue(Warehouse.getProducts().contains(products.get(3)));
    }

}