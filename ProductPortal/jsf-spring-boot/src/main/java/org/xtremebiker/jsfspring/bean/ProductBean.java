package org.xtremebiker.jsfspring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.xtremebiker.jsfspring.dao.Product;
import org.xtremebiker.jsfspring.util.Constant;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
@Scope("view")
public class ProductBean {
    private Product product = new Product();
    private Product newProduct = new Product();
    private List<Product> productList;
    private Long productIdToDelete;

    private final WebClient webClient;

    @Autowired
    public ProductBean( WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @PostConstruct
    public void init() {
        loadProductList();
    }

    // Get all products
    private void loadProductList() {
        Product[] products = webClient.get()
                .uri(Constant.GET_ALL_PRODUCT_URL)
                .retrieve()
                .bodyToMono(Product[].class)
                .block(Duration.ofSeconds(1));

        if (products != null) {
            if (products.length > 0) {
                fillAndSortProductsList(products);
            }
        }
    }

    // Add new product
    public String addProduct() {
        webClient.post()
                .uri(Constant.ADD_PRODUCT_URL)
                .bodyValue(newProduct)
                .retrieve()
                .bodyToMono(Product.class)
                .subscribe(createdProduct -> {
                    newProduct = new Product();
                    loadProductList();
                }, error -> {
                    error.printStackTrace();
                });
        return redirectTo(Constant.HOME_PAGE);
    }

    public void prepareDelete(Product product) {
        this.productIdToDelete = product.getId();
    }

    // Delete specific product
    public String deleteProduct() {
        webClient.delete()
                .uri(Constant.DELETE_PRODUCT_URL + productIdToDelete)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(result -> {
                    loadProductList();


                }, error -> {
                    error.printStackTrace();
                });
        return redirectTo(Constant.HOME_PAGE);
    }
    public void prepareEdit(Product product) {
        if (product != null) {
            this.product = product;
        } else {
            this.product = new Product();
        }
    }

    // Update Product
    public void updateProduct() {
        webClient.put()
                .uri(Constant.UPDATE_PRODUCT_URL + product.getId())
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(result -> {
                    loadProductList();
                }, error -> {
                    error.printStackTrace();
                });
    }

    // Reload the page
    private String redirectTo(String path) {
        return path + "?faces-redirect=true";
    }

    // Fill and sort productsList
    private void fillAndSortProductsList(Product[] products) {
        productList = Arrays.asList(products);
        productList.sort(Comparator.comparing(Product::getId));
    }

    public Product getProduct() {
        return product;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            loadProductList();
        }
        return productList;
    }


}
