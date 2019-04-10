package ru.toppavel.dao;


import org.postgresql.util.PSQLException;
import ru.toppavel.models.Brand;

import java.util.List;
import java.util.Optional;


public interface BrandDao {
    void addBrandToDb(String name);

    Optional<Brand> getBrandByID(int id);

    void updateBrand(String name, int id) throws PSQLException;

    void deleteBrand(int id);

    List<Brand> getAllBrands();

    Optional<Integer> getIdByName(String name);
}
