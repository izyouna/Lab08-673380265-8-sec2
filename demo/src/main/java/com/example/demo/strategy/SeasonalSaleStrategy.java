package com.example.demo.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public Double calculateDiscount(Double price) {
        return price * 0.8; // 20% discount for seasonal sale
    }
    
}
