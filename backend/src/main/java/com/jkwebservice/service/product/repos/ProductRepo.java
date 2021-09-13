package com.jkwebservice.service.product.repos;

import com.jkwebservice.service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
