package com.example.demo.repository;                                                                                                                    
                                                                                                                                                            
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductDetail;                                                                                                            
                                                                                                                                                            
@Repository                                                                                                                                             
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {                                                                   
                                                                                                                                                            
    // ค้นหารายละเอียดสินค้าด้วย Product ID                                                                                                                  
    Optional<ProductDetail> findByProductId(Long productId);                                                                                            
}           