package ru.toppavel.service;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.toppavel.dao.BrandDao;
import ru.toppavel.exception.BrandNotFoundException;
import ru.toppavel.exception.UpdateException;
import ru.toppavel.models.Brand;

import java.util.List;


@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao dao;

    @Override
    public void addBrandToDb(String name) {
        dao.addBrandToDb(name);
    }

    @Override
    public Brand getBrandById(int id) {
        return dao.getBrandByID(id).orElseThrow(() -> new BrandNotFoundException(id));
    }

    @Override
    public void updateBrand(String name, int id) {
        dao.getBrandByID(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        try {
            dao.updateBrand(name, id);
        } catch (PSQLException e) {
            throw new UpdateException("Brand do not update", e);
        }
    }

    @Override
    public void deleteBrand(int id) {
        dao.getBrandByID(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        dao.deleteBrand(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return dao.getAllBrands();
    }

    @Override
    public Integer getIdByName(String name){
        return dao.getIdByName(name).orElseThrow(() -> new BrandNotFoundException(name));
    }
}
