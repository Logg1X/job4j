package ru.job4j.car.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.car.models.*;

import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CarStorageTest {

    private SessionFactory factory;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("todo.cfg.xml")
                .build();
        try {
            this.factory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private <T> T wrap(Function<Session, T> comand) {
        T result = null;
        try (Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                result = comand.apply(session);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
        return result;
    }

    @Test
    public void whenAddCarThenGetBrand() {
        Brand brand = new Brand(1, "Nissan");
        int carId = this.wrap(session -> {
            Car car = new Car();
            car.setCarcaseByIdCarcase(new Carcase(1));
            car.setBrandByIdBrand(new Brand(1));
            car.setColorByIdColor(new Color(1));
            car.setWdByIdWd(new Wd(2));
            car.setName("My car");
            int id = (int) session.save(car);
            return id;
        });
        Car car1 = this.wrap(session -> {
            Car car = session.get(Car.class, carId);
            assertTrue(session.createQuery("from Car ").list().contains(car));
            assertThat(car.getBrandByIdBrand(), is(brand));
            return car;
        });
        this.wrap(session -> {
            session.remove(car1);
            assertTrue(!session.createQuery("from Car ").list().contains(car1));
            return null;
        });
    }

    @Test
    public void whenAddBrandThenReturnBrand() {
        Brand brand = new Brand();
        brand.setIname("VAZ");
        int id = this.wrap(session -> {
            Brand brand1 = new Brand();
            brand1.setIname("VAZ");
            return (int) session.save(brand1);
        });
        this.wrap(session -> {
            Brand b = session.get(Brand.class, id);
            session.remove(b);
            assertThat(b.getIname(), is("VAZ"));
            return null;
        });
    }
}