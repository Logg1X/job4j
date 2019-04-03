package ru.job4j.car.storage;

import ru.job4j.car.models.Car;

public interface Storage {
    void add(Car car);

    void update(Car car);

    void delete(int id);
}
