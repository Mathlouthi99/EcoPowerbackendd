package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    void add(ProductCategory category);
    ProductCategory update(ProductCategory category);
    List<ProductCategory> getAll();
    ProductCategory getById(long id);
    void remove(long id);
}
