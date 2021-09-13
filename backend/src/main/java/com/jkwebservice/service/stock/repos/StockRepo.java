package com.jkwebservice.service.stock.repos;

import com.jkwebservice.service.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {
}
