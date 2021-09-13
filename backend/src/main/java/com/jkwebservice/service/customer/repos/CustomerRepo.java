package com.jkwebservice.service.customer.repos;

import com.jkwebservice.service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
}
