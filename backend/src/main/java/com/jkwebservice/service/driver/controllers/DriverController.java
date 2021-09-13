package com.jkwebservice.service.driver.controllers;

import com.jkwebservice.service.driver.repos.DriverRepo;
import com.jkwebservice.service.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DriverController {

    @Autowired
    private DriverRepo driverRepo;

    @GetMapping("/driver")
    public Driver getDriver(@PathVariable Long id){

        Driver driver = new Driver();

        Optional<Driver> details = driverRepo.findById(id);
        if (details.isPresent()){
            driver = details.get();
        }
        return driver;
    }

    public List<Driver> getDrivers(){

        return driverRepo.findAll();

    }

    @PutMapping("/driver/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driverData){


        Driver updatedDriver = new Driver();

        Optional<Driver> details = driverRepo.findById(id);
        if (details.isPresent()){
            Driver driver = new Driver();
            driver = details.get();

            driver.setId(driverData.getId());
            driver.setNic(driverData.getNic());
            driver.setName(driverData.getName());
            driver.setLocation(driverData.getLocation());
            driver.setContact(driverData.getContact());

            updatedDriver = driverRepo.save(driver);

        }
        return updatedDriver;
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id){
        Optional<Driver> branch = driverRepo.findById(id);
        if (branch.isPresent()){
            Driver driver1 = new Driver();
            driver1 = branch.get();

            driverRepo.delete(driver1);
        }

        return ResponseEntity.ok().build();
    }
}
