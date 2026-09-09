package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository 
public interface ProductRepository extends JpaRepository<Product, Long> {
    // ค้นหาสินค้าตามหมวดหมู่ (Category)                                                                                                                     
        List<Product> findByCategory(String category);                                                                                                      
                                                                                                                                                            
        // ค้นหาสินค้าตามชื่อ (ค้นหาแบบมีคำที่ตรงกัน โดยไม่สนใจตัวพิมพ์เล็ก-ใหญ่)                                                                                            
        List<Product> findByNameContainingIgnoreCase(String name);                                                                                          
                                                                                                                                                            
        // ค้นหาสินค้าตามยี่ห้อ (Brand)                                                                                                                           
        List<Product> findByBrand(String brand);  
}
