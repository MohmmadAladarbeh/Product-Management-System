package org.example.productsservice.service;

import org.example.productsservice.exception.InvalidInputException;
import org.example.productsservice.exception.ResourceNotFoundException;
import org.example.productsservice.model.Product;
import org.example.productsservice.repository.ProductRepository;
import org.example.productsservice.util.Global;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAllActiveProducts();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products have been founded");
        }
        return products;
    }

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
        return productRepository.save(product);
    }

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
            return productRepository.save(updatedProduct);
        }else {
            throw new InvalidInputException("Product with {" + id + "} Id not find");
        }
    }
    public boolean softDeleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent() && !optionalProduct.get().getDeleted()) {
            Product product = optionalProduct.get();
            product.setDeleted(true);
            productRepository.save(product);
            return true;
        }else {
            throw new InvalidInputException("Product with {" + id + "} Id not find");
        }
    }
}
