package com.example.demo.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override 
    public Double calculateDiscount(Double price) {
        return price;
    }
}
