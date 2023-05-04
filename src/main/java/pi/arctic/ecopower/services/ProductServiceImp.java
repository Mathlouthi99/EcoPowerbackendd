package pi.arctic.ecopower.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.repositories.ProductRepo;

import pi.arctic.ecopower.services.IProductService;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepo productRepository;

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product findProdById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

}

