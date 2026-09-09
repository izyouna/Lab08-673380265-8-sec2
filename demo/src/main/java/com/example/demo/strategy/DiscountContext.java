package com.example.demo.strategy;
import org.springframework.stereotype.Component;

import com.example.demo.model.Product;

@Component
public class DiscountContext {
    public Double calculateFinalPrice(Product product){
        if (product == null || product.getPrice() == null) {
            return 0.0;
        }

        String discountType = product.getDiscountType() != null ? product.getDiscountType().toUpperCase() : "NONE";
        DiscountStrategy discountStrategy = switch (discountType) {
            case "MEMBER" -> new MemberDiscountStrategy();
            case "SEASONAL" -> new SeasonalSaleStrategy();
            default -> new NoDiscountStrategy();
        };

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
