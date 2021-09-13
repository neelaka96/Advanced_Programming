package com.jkwebservice.service.vehicle.repos;

import com.jkwebservice.service.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
