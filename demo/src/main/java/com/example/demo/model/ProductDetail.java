package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "product_details")
public class ProductDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String warranty;
    private String weight;
    private String dimensions;
    private String manufacturerCountry;

    // 1:1 with Product (SRP)
    @OneToOne(mappedBy = "detail")
    private Product product;

    public ProductDetail() {
    }

    public ProductDetail(String description, String warranty, String weight, String dimensions, String manufacturerCountry) {
        this.description = description;
        this.warranty = warranty;
        this.weight = weight;
        this.dimensions = dimensions;
        this.manufacturerCountry = manufacturerCountry;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public Product getProduct() {
        return product;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
}
