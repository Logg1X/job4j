package ru.toppavel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.toppavel.models.Brand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BrandDaoImpl implements BrandDao {
    private static final String ADD_BRAND = "insert into brand (name) values (:name) on conflict (name) do nothing";
    private static final String GET_BRAND_BY_ID = "Select * from brand where id = :id";
    private static final String UPDATE_BRAND = "update brand set name = :name where id = :id";
    private static final String DELETE_BRAND = "delete from brand where id = :id";
    private static final String GET_ALL_BRANDS = "select * from brand";
    private static final String GET_ID_BRAND_BY_NAME = "select * from brand where name = :name";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void addBrandToDb(String name) {
        jdbcTemplate.update(ADD_BRAND, Map.of("name", name));
    }

    @Override
    public Optional<Brand> getBrandByID(int id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BRAND_BY_ID, Map.of("id", id), brandRowMapper)
            );

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public void updateBrand(String name, int id){
        jdbcTemplate.update(UPDATE_BRAND, Map.of("name", name, "id", id));
    }

    @Override
    public void deleteBrand(int id) {
        jdbcTemplate.update(DELETE_BRAND, Map.of("id", id));
    }

    @Override
    public List<Brand> getAllBrands() {
        return jdbcTemplate.query(GET_ALL_BRANDS, brandRowMapper);
    }

    @Override
    public Optional<Integer> getIdByName(String name) {
        Optional<Integer> result;
        try {
            result = Optional.of(jdbcTemplate.queryForObject(GET_ID_BRAND_BY_NAME, Map.of("name", name), brandRowMapper).getId());
        }catch (EmptyResultDataAccessException e) {
            result =  Optional.empty();
        }
        return result;
    }

    private RowMapper<Brand> brandRowMapper = ((resultSet, i) -> {
        Brand brand = new Brand();
        brand.setId(resultSet.getInt("id"));
        brand.setName(resultSet.getString("name"));
        return brand;
    });
}
