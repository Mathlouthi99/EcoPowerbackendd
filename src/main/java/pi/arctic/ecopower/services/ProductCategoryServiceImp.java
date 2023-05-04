package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.ProductCategory;
import pi.arctic.ecopower.repositories.ProductCategoryRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImp implements IProductCategoryService{
    private ProductCategoryRepo productCategoryRepo;
    @Override
    public void add(ProductCategory category) {

        productCategoryRepo.save(category);
    }

    @Override
    public ProductCategory update(ProductCategory category) {
        return productCategoryRepo.save(category);
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public ProductCategory getById(long id) {
        return productCategoryRepo.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        productCategoryRepo.deleteById(id);
    }
}
