package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.Product;

public interface IProductService {
    Product findProductById(Long productId);
    void updateProduct(Product product);


    void save(Product product) ;

    Product findProdById(Long productId);

    void deleteProduct(Long productId);

    Product addProduct(Product product);



}
