package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductDetail;
import com.example.demo.model.Review;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ReviewRepository reviewRepository;

    // DIP: Constructor Injection depending on Abstractions (Repositories)
    public ProductService(ProductRepository productRepository,
                          ProductDetailRepository productDetailRepository,
                          ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        // 1:1 Relationship (Product <-> ProductDetail)
        if (product.getDetail() != null) {
            product.getDetail().setProduct(product);
        }

        // 1:N Relationship (Product <-> Review)
        if (product.getReviews() != null) {
            List<Review> validReviews = new ArrayList<>();
            for (Review review : product.getReviews()) {
                if (review.getReviewer() != null && !review.getReviewer().trim().isEmpty()) {
                    review.setProduct(product);
                    if (review.getReviewDate() == null) {
                        review.setReviewDate(LocalDate.now());
                    }
                    validReviews.add(review);
                }
            }
            product.setReviews(validReviews);
        }

        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Product existing = productRepository.findById(product.getId()).orElse(null);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setCategory(product.getCategory());
            existing.setBrand(product.getBrand());
            existing.setStock(product.getStock());
            existing.setPrice(product.getPrice());
            existing.setDiscountType(product.getDiscountType());

            // 1:1 Update ProductDetail
            if (product.getDetail() != null) {
                if (existing.getDetail() == null) {
                    product.getDetail().setProduct(existing);
                    existing.setDetail(product.getDetail());
                } else {
                    existing.getDetail().setDescription(product.getDetail().getDescription());
                    existing.getDetail().setWarranty(product.getDetail().getWarranty());
                    existing.getDetail().setWeight(product.getDetail().getWeight());
                    existing.getDetail().setDimensions(product.getDetail().getDimensions());
                    existing.getDetail().setManufacturedCountry(product.getDetail().getManufacturedCountry());
                }
            }

            productRepository.save(existing);
        } else {
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public ProductDetail getProductDetailByProductId(Long productId) {
        return productDetailRepository.findByProductId(productId).orElse(null);
    }
}
