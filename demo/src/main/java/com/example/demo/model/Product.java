package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "products")
public class Product {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private String brand;
    @Column
    private Integer stock;
    @Column
    private Double price;
    @Column
    private String discountType;

    // 1:1 with ProductDetail (SRP)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private ProductDetail detail;

    // 1:N with Review (OCP)
    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL
    )
    private List<Review> reviews = new ArrayList<>();

    public Product() {
    }
    
    public Product(String name, String category, String brand, Integer stock, Double price, String discountType) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        this.price = price;
        this.discountType = discountType;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getDiscountType() {
        return discountType;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    
    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public void setDetail(ProductDetail detail) {
        this.detail = detail;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDiscountName() {
        com.example.demo.strategy.DiscountContext context = new com.example.demo.strategy.DiscountContext();
        return context.getDiscountName(this.discountType);
    }

    public Double getDiscountedPrice() {
        com.example.demo.strategy.DiscountContext context = new com.example.demo.strategy.DiscountContext();
        return context.calculateFinalPrice(this);
    }

    public Double getFinalPrice() {
        return getDiscountedPrice();
    }

    public void addReview(Review review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
        review.setProduct(this);
    }
        
}
