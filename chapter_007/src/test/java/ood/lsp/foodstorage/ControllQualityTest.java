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
        controllQuality.addStorage("Shop", new Shop("Shop", 10));
        controllQuality.addStorage("Warehouse", new Warehouse("Shop", 10));
        controllQuality.addStorage("Trash", new Trash("Shop", 10));
        List<Product> products = List.of(
                new Food("Eat1",
                        LocalDate.of(2019, 02, 20),
                        LocalDate.of(2019, 01, 01),
                        19.0,
                        0),
                new Food("Eat2",
                        LocalDate.of(2019, 02, 28),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0),
                new Food("Eat3",
                        LocalDate.of(2019, 02, 9),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0),
                new Food("Eat4",
                        LocalDate.of(2019, 03, 28),
                        LocalDate.of(2019, 02, 1),
                        16.0,
                        0));
        controllQuality.sortProductsByStorage(products);
        assertThat(controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().size(), is(2));
        assertTrue(controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().contains(products.get(1)));
        assertTrue(controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().contains(products.get(0)));
        assertThat(controllQuality.getAllStorage().get("Trash").getAllProductsInStorage().size(), is(1));
        assertTrue(controllQuality.getAllStorage().get("Trash").getAllProductsInStorage().contains(products.get(2)));
        assertThat(controllQuality.getAllStorage().get("Warehouse").getAllProductsInStorage().size(), is(1));
        assertTrue(controllQuality.getAllStorage().get("Warehouse").getAllProductsInStorage().contains(products.get(3)));
    }
}