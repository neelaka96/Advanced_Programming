package com.jkwebservice.service.user.controllers;

import com.jkwebservice.service.models.User;
import com.jkwebservice.service.user.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;
    private Integer finalResult;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {

        User user = new User();

        Optional<User> details = userRepo.findById(id);
        if (details.isPresent()) {
            user = details.get();
        }

        return user;
    }

    @GetMapping("/user")
    public List<User> getUsers() {

        return userRepo.findAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){

        return userRepo.save(user);

    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userData){


        User updatedUser = new User();

        Optional<User> details = userRepo.findById(id);
        if (details.isPresent()){
            User user = new User();
            user = details.get();

            user.setId(userData.getId());
            user.setNic(userData.getNic());
            user.setName(userData.getName());
            user.setUserType(userData.getUserType());
            user.setAddress(userData.getAddress());
            user.setContact(userData.getContact());
            user.setBranchId(userData.getBranchId());
            user.setUserName(userData.getUserName());
            user.setPassword(userData.getPassword());

            updatedUser = userRepo.save(user);

        }
        return updatedUser;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            User user1 = new User();
            user1 = user.get();

            userRepo.delete(user1);
        }

        return ResponseEntity.ok().build();
    }
}
