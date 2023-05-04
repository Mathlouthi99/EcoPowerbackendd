package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.entities.ProductCategory;
import pi.arctic.ecopower.services.IProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryProductController {
    private IProductCategoryService iProductCategoryService;

    @PostMapping("/add")
    void add(@RequestBody ProductCategory category){
        iProductCategoryService.add(category);
    }
    @PutMapping("/update")
    ProductCategory update(@RequestBody ProductCategory category){
        return iProductCategoryService.update(category);
    }
    @GetMapping("/all")
    List<ProductCategory> getAll(){
        return iProductCategoryService.getAll();
    }
    @GetMapping("/get/{id}")
    ProductCategory getById(@PathVariable long id){
        return iProductCategoryService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable long id){
        iProductCategoryService.remove(id);
    }
}
