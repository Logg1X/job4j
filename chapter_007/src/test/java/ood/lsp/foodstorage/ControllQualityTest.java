package ood.lsp.foodstorage;

import ood.lsp.foodstorage.products.Food;
import ood.lsp.foodstorage.products.Product;
import ood.lsp.foodstorage.storage.Shop;
import ood.lsp.foodstorage.storage.Trash;
import ood.lsp.foodstorage.storage.Warehouse;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControllQualityTest {


    @Test
    public void whenSortProductByStorageThenShopTrashWarehouse() {
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.addStorage("Shop", new Shop("Shop", 3));
        controllQuality.addStorage("Warehouse", new Warehouse("Shop", 10));
        controllQuality.addStorage("Trash", new Trash("Shop", 10));
        List<Product> products = List.of(
                new Food("Eat1",
                        LocalDate.now().plusDays(7),
                        LocalDate.now().minusDays(45),
                        19.0,
                        0),
                new Food("Eat2",
                        LocalDate.now().plusDays(25),
                        LocalDate.now().minusDays(55),
                        16.0,
                        0),
                new Food("Eat3",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(12),
                        16.0,
                        0),
                new Food("Eat4",
                        LocalDate.now().plusDays(110),
                        LocalDate.now().minusDays(2),
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

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenStorageOverflowThenShopIsFull() {
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.addStorage("Shop", new Shop("Shop", 1));
        List<Product> products = List.of(
                new Food("Eat1",
                        LocalDate.now().plusDays(7),
                        LocalDate.now().minusDays(45),
                        19.0,
                        0),
                new Food("Eat2",
                        LocalDate.now().plusDays(25),
                        LocalDate.now().minusDays(55),
                        16.0,
                        0));
        controllQuality.sortProductsByStorage(products);
    }

}