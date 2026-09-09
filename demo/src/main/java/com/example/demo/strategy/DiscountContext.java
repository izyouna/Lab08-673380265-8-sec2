package com.example.demo.strategy;
import org.springframework.stereotype.Component;

import com.example.demo.model.Product;

@Component
public class DiscountContext {
    public Double calculateFinalPrice(Product product){

        // check product is null or not
        if(product == null || product.getPrice() == null || product.getDiscountType() == null){
            throw new IllegalArgumentException("Product, price, and discount type must not be null");
        }

        String discountType = product.getDiscountType() != null ? product.getDiscountType().toUpperCase() : "NONE";
        DiscountStrategy discountStrategy;

        switch (discountType) {
            case "NONE" -> discountStrategy = new NoDiscountStrategy();
            case "MEMBER" -> discountStrategy = new MemberDiscountStrategy();
            case "SEASONAL" -> discountStrategy = new SeasonalSaleStrategy();
            default -> {
                discountStrategy = new NoDiscountStrategy();
            }
        }

        return discountStrategy.calculateDiscount(product.getPrice());
    }

    public String getDiscountName(String discountType) {
        if (discountType == null) return "ราคาปกติ";
        return switch (discountType.toUpperCase()) {
            case "MEMBER" -> "ส่วนลดสมาชิก (10%)";
            case "SEASONAL" -> "ส่วนลดเทศกาล (20%)";
            default -> "ราคาปกติ";
        };
    }
}
