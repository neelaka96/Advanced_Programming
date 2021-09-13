package com.jkwebservice.service.vehicle.controllers;

import com.jkwebservice.service.models.Vehicle;
import com.jkwebservice.service.vehicle.repos.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepo vehicleRepo;
    private Integer finalResult;

    @GetMapping("/vehicle/{id}")
    public Vehicle getVehicle(@PathVariable Long id) {

        Vehicle vehicle = new Vehicle();

        Optional<Vehicle> details = vehicleRepo.findById(id);
        if (details.isPresent()) {
            vehicle = details.get();
        }

        return vehicle;
    }

    @GetMapping("/vehicle")
    public List<Vehicle> getVehicles() {

        return vehicleRepo.findAll();
    }

    @PostMapping("/vehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){

        return vehicleRepo.save(vehicle);

    }

    @PutMapping("/vehicle/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleData){


        Vehicle updatedVechile = new Vehicle();

        Optional<Vehicle> details = vehicleRepo.findById(id);
        if (details.isPresent()){
            Vehicle vehicle = new Vehicle();
            vehicle = details.get();

            vehicle.setDriverId(vehicleData.getDriverId());
            vehicle.setId(vehicleData.getId());
            vehicle.setVechicleNo(vehicleData.getVechicleNo());
            vehicle.setModel(vehicleData.getModel());
            vehicle.setType(vehicleData.getType());

            updatedVechile = vehicleRepo.save(vehicle);

        }
        return updatedVechile;
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (vehicle.isPresent()){
            Vehicle vehicle1 = new Vehicle();
            vehicle1 = vehicle.get();

            vehicleRepo.delete(vehicle1);
        }

        return ResponseEntity.ok().build();
    }
}
