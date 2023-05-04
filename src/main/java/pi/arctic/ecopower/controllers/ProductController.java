package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.ProductCategory;
import pi.arctic.ecopower.services.IProductService;
import pi.arctic.ecopower.services.ProductServiceImp;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductServiceImp productServiceImp;


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productServiceImp.addProduct(product);
        if (createdProduct == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        Product existingProduct = productService.findProductById(productId);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productService.updateProduct(existingProduct);
        return ResponseEntity.ok(existingProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Product product = productService.findProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}

