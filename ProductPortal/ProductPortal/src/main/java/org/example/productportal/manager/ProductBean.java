package org.example.productportal.manager;

import org.example.productportal.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProductBean {
    private Product product = new Product();
    private List<Product> productList;

//    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getProductList() {
        if (productList == null) {
            productList = Arrays.asList(restTemplate.getForObject("http://localhost:8080/api/products", Product[].class));
        }
        return productList;
    }

    public void addProduct() {
        restTemplate.postForObject("http://localhost:8080/api/products", product, Product.class);
        productList.add(product);
    }
}
