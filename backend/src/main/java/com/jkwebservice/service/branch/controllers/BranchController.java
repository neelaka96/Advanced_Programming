package com.jkwebservice.service.branch.controllers;

import com.jkwebservice.service.branch.repos.BranchRepo;
import com.jkwebservice.service.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BranchController {

    @Autowired
    private BranchRepo branchRepo;
    private Integer finalResult;

    @GetMapping("/branch/{id}")
    public Branch getBranch(@PathVariable Long id) {

        Branch branch = new Branch();

        Optional<Branch> details = branchRepo.findById(id);
        if (details.isPresent()) {
            branch = details.get();
        }

        return branch;
    }

    @GetMapping("/branch")
    public List<Branch> getBranches() {

        return branchRepo.findAll();
    }

    @PostMapping("/branch")
    public Branch addBranch(@RequestBody Branch branch){

        return branchRepo.save(branch);

    }

    @PutMapping("/branch/{id}")
    public Branch updateBranch(@PathVariable Long id, @RequestBody Branch branchData){


        Branch updatedBranch = new Branch();

        Optional<Branch> details = branchRepo.findById(id);
        if (details.isPresent()){
            Branch branch = new Branch();
            branch = details.get();

            branch.setLocation(branchData.getLocation());
            branch.setName(branchData.getName());
            branch.setContact(branchData.getContact());
            branch.setId(branchData.getId());

            updatedBranch = branchRepo.save(branch);

        }
        return updatedBranch;
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long id){
        Optional<Branch> branch = branchRepo.findById(id);
        if (branch.isPresent()){
            Branch branch1 = new Branch();
            branch1 = branch.get();

            branchRepo.delete(branch1);
        }

        return ResponseEntity.ok().build();
    }
}