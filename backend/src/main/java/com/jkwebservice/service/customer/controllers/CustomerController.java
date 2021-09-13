package com.jkwebservice.service.customer.controllers;

import com.jkwebservice.service.customer.repos.CustomerRepo;
import com.jkwebservice.service.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/customer")
    public Customer getCustomer(@PathVariable Long id){

        Customer customer = new Customer();

        Optional<Customer> details = customerRepo.findById(id);
        if (details.isPresent()){
            customer = details.get();
        }
        return customer;
    }

    public List<Customer> getCustomers(){

        return customerRepo.findAll();

    }

    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerData){


        Customer updatedCustomer = new Customer();

        Optional<Customer> details = customerRepo.findById(id);
        if (details.isPresent()){
            Customer customer = new Customer();
            customer = details.get();

            customer.setLocation(customerData.getLocation());
            customer.setId(customerData.getId());
            customer.setName(customerData.getName());
            customer.setContact(customerData.getContact());
            customer.setNic(customerData.getNic());

            updatedCustomer = customerRepo.save(customer);

        }
        return updatedCustomer;
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            Customer customer1 = new Customer();
            customer1 = customer.get();

            customerRepo.delete(customer1);
        }

        return ResponseEntity.ok().build();
    }
}



