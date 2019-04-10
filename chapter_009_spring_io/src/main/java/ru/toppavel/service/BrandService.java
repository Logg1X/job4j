package ru.toppavel.service;

import ru.toppavel.models.Brand;

import java.util.List;

public interface BrandService {
    void addBrandToDb(String name);

    Brand getBrandById(int id);

    void updateBrand(String name, int id);

    void deleteBrand(int id);

    List<Brand> getAllBrands();

    Integer getIdByName(String name);
}
