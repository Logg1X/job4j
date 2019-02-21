package ood.lsp.foodstorage;

import ood.lsp.foodstorage.products.Food;
import ood.lsp.foodstorage.products.Perishable;
import ood.lsp.foodstorage.products.Product;
import ood.lsp.foodstorage.products.RecyclableFood;
import ood.lsp.foodstorage.storage.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test(expected = IndexOutOfBoundsException.class)
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

    @Test
    public void whenSortProductsWithCanReproductPointThenAddInProcessingStorage() {
        List<Product> products = List.of(
                new Food("Eat3",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(12),
                        16.0,
                        0),
                new RecyclableFood(new Food("Eat4",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(15),
                        19.0,
                        0))
        );
        ControllQuality cq = new ControllQuality();
        cq.addStorage("Processing", new Processing(new Trash("Processing", 5)));
        cq.addStorage("Trash", new SmartTrash(new Trash("Trash", 2)));
        cq.sortProductsByStorage(products);
        assertThat(cq.getAllStorage().get("Processing").getAllProductsInStorage().size(), is(1));
        assertThat(cq.getAllStorage().get("Trash").getAllProductsInStorage().size(), is(1));
    }

    @Test
    public void whenSortProductsWithKeepRefrigeratedPointThenAddInColdStorage() {
        List<Product> products = List.of(
                new Food("Eat4",
                        LocalDate.now().plusDays(110),
                        LocalDate.now().minusDays(2),
                        16.0,
                        0),
                new Perishable(new Food("Eat5",
                        LocalDate.now().plusDays(110),
                        LocalDate.now().minusDays(2),
                        16.0,
                        0)
                ),
                new Perishable(new Food("Eat6",
                        LocalDate.now().plusDays(100),
                        LocalDate.now().minusDays(3),
                        25.0,
                        0)
                )
        );
        ControllQuality cq = new ControllQuality();
        cq.addStorage("Fridge", new ColdStorage(new Warehouse("Fridge", 5)));
        cq.addStorage("Warehouse", new SmartWarehouse(new Warehouse("Warehouse", 2)));
        cq.sortProductsByStorage(products);
        assertThat(cq.getAllStorage().get("Fridge").getAllProductsInStorage().size(), is(2));
        assertThat(cq.getAllStorage().get("Warehouse").getAllProductsInStorage().size(), is(1));
    }

    @Test
    public void whenResortAllProductInAllStoragesThenResort() {
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.addStorage("Shop", new Shop("Shop", 10));
        controllQuality.addStorage("Warehouse", new SmartWarehouse(new Warehouse("SmartWarehouse", 10)));
        controllQuality.addStorage("Trash", new SmartTrash(new Trash("Shop", 10)));
        controllQuality.addStorage("Processing", new Processing(new Trash("Processing", 5)));
        controllQuality.addStorage("Fridge", new ColdStorage(new Warehouse("Fridge", 5)));
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
                new RecyclableFood(new Food("Eat4",
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(15),
                        19.0,
                        0)),
                new Food("Eat5",
                        LocalDate.now().plusDays(110),
                        LocalDate.now().minusDays(2),
                        16.0,
                        0),
                new Perishable(new Food("Eat6",
                        LocalDate.now().plusDays(110),
                        LocalDate.now().minusDays(2),
                        16.0,
                        0)),
                new Perishable(new Food("Eat7",
                        LocalDate.now().plusDays(100),
                        LocalDate.now().minusDays(3),
                        25.0,
                        0)
                ));
        controllQuality.sortProductsByStorage(products);
        assertThat(controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().size(), is(2));
        assertThat(controllQuality.getAllStorage().get("Warehouse").getAllProductsInStorage().size(), is(1));
        assertThat(controllQuality.getAllStorage().get("Trash").getAllProductsInStorage().size(), is(1));
        assertThat(controllQuality.getAllStorage().get("Processing").getAllProductsInStorage().size(), is(1));
        assertThat(controllQuality.getAllStorage().get("Fridge").getAllProductsInStorage().size(), is(2));
        controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().get(1).setExpaireDate(LocalDate.now().minusDays(1));
        controllQuality.resort();
        assertThat(controllQuality.getAllStorage().get("Shop").getAllProductsInStorage().size(), is(1));
        assertThat(controllQuality.getAllStorage().get("Trash").getAllProductsInStorage().size(), is(2));
    }
}