package org.example.productportal.manager;

import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import org.example.productportal.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProductBean {
    private Product product = new Product();
    private List<Product> productList;

    // Use Spring's @Autowired to inject the RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    // Getter for the product list
    public List<Product> getProductList() {
        if (productList == null) {
            // Fetch the product list from the REST API and assign it
            productList = Arrays.asList(restTemplate.getForObject("http://localhost:8080/api/products", Product[].class));
        }
        return productList;
    }

    // Add a new product using the RestTemplate
    public void addProduct() {
        restTemplate.postForObject("http://localhost:8080/api/products", product, Product.class);
        productList.add(product);
    }

    // Getter for the Product object
    public Product getProduct() {
        return product;
    }

    // Setter for the Product object
    public void setProduct(Product product) {
        this.product = product;
    }
}