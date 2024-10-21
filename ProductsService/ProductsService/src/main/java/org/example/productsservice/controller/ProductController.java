package org.example.productsservice.controller;


import org.example.productsservice.model.Product;
import org.example.productsservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/v1")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        Product product1 = productService.addProduct(product);
        return product1;
    }
}
