package com.example.demo.strategy;

public class MemberDiscountStrategy implements DiscountStrategy {
    @Override
    public Double calculateDiscount(Double price) {
        return price * 0.9; // 10% discount for members
    }
    
}
