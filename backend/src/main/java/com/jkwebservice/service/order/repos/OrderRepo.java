package com.jkwebservice.service.order.repos;

import com.jkwebservice.service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
