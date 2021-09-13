package com.jkwebservice.service.driver.repos;

import com.jkwebservice.service.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {
}
