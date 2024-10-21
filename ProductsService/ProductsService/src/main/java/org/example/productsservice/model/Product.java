package org.example.productsservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_id")
    private Long id;

    private String name;

//    @Column(name = "discription")
    private String disc;

    private Double price;
}
