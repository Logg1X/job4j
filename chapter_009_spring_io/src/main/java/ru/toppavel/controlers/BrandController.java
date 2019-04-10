package ru.toppavel.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.toppavel.models.Brand;
import ru.toppavel.service.BrandService;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/{id:\\d+}")
    public Brand getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBrand(@RequestBody String request) {
        brandService.addBrandToDb(request);
    }

    @PutMapping(value = "/{id:\\d+}")
    public void updateBrand(@RequestBody @NotNull String name,
                            @PathVariable int id){
        brandService.updateBrand(name, id);
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void deleteBrandById(@PathVariable int id) {
        brandService.deleteBrand(id);
    }

    @GetMapping()
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping(value = "/name/{name}")
    public Integer getBrandByName(@PathVariable String name) {
        return brandService.getIdByName(name);
    }

}
