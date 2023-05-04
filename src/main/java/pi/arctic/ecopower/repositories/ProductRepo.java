package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.ProductCategory;

import java.util.List;

@Repository

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByProductCategory(ProductCategory category);

}
