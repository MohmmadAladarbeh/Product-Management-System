package org.example.productsservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.productsservice.exception.InvalidInputException;
import org.example.productsservice.exception.ResourceNotFoundException;
import org.example.productsservice.model.Product;
import org.example.productsservice.repository.ProductRepository;
import org.example.productsservice.util.Global;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    @Cacheable(value = "productsCache", unless = "#result != null && #result.isEmpty()")
    public List<Product> getAllProducts() {
        log.info("Fetching all active products");
        List<Product> products = productRepository.findAllActiveProducts();
        if (products.isEmpty()) {
            log.info("No products have been founded");
            return products;
        }
        log.info("Fetched {} active products", products.size());
        return products;
    }

    // Add new product
    public Product addProduct(Product product) {
        if (Global.isEmptyString(product.getName()).isEmpty()) {
            throw new InvalidInputException("Product's name is required");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidInputException("Product's price is required");
        }
        if (Global.isEmptyString(product.getDescription()).isEmpty()) {
            throw new InvalidInputException("Product description is required");
        }
        log.info("Add new {} products", product.getName());
        return productRepository.save(product);
    }

    // Update specific product
    public Product updateProduct(Long id, Product newProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent() && !productOptional.get().getDeleted()) {
            Product updatedProduct = productOptional.get();

            if (newProduct.getName() != null) {
                updatedProduct.setName(newProduct.getName());
            }
            if (newProduct.getDescription() != null) {
                updatedProduct.setDescription(newProduct.getDescription());
            }
            if (newProduct.getPrice() != null) {
                updatedProduct.setPrice(newProduct.getPrice());
            }
            updatedProduct.setDeleted(false);
            log.info("Updated {} products", updatedProduct.getName());
            return productRepository.save(updatedProduct);
        }else {
            throw new InvalidInputException("Product with {" + id + "} Id not find");
        }
    }

    // Delete specific product
    public boolean softDeleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent() && !optionalProduct.get().getDeleted()) {
            Product product = optionalProduct.get();
            product.setDeleted(true);
            log.info("Deleted {} products", product.getName());
            productRepository.save(product);
            return true;
        }else {
            throw new InvalidInputException("Product with {" + id + "} Id not find");
        }
    }
}
