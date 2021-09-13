package com.jkwebservice.service.branchproduct.controllers;

import com.jkwebservice.service.branchproduct.repos.BranchProductRepo;
import com.jkwebservice.service.models.BranchProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BranchProductController {

    @Autowired
    private BranchProductRepo branchProductRepo;
    private Integer finalResult;

    @GetMapping("/branchProduct/{id}")
    public BranchProduct getBranchProduct(@PathVariable Long id) {

        BranchProduct branchProduct = new BranchProduct();

        Optional<BranchProduct> details = branchProductRepo.findById(id);
        if (details.isPresent()) {
            branchProduct = details.get();
        }

        return branchProduct;
    }

    @GetMapping("/branchProduct")
    public List<BranchProduct> getBranchProducts() {

        return branchProductRepo.findAll();
    }

    @PostMapping("/branchProduct")
    public BranchProduct addBranchProduct(@RequestBody BranchProduct branchProduct){

        return branchProductRepo.save(branchProduct);

    }

    @PutMapping("/branchProduct/{id}")
    public BranchProduct updateBranchProduct(@PathVariable Long id, @RequestBody BranchProduct branchProductData){


        BranchProduct updateBranchProduct = new BranchProduct();

        Optional<BranchProduct> details = branchProductRepo.findById(id);
        if (details.isPresent()){
            BranchProduct branchProduct = new BranchProduct();
            branchProduct = details.get();

            branchProduct.setId(branchProductData.getId());
            branchProduct.setCustomerId(branchProductData.getCustomerId());
            branchProduct.setDate(branchProductData.getDate());
            branchProduct.setBranchId(branchProduct.getBranchId());
            branchProduct.setProductId(branchProduct.getProductId());
            branchProduct.setStockQty(branchProduct.getStockQty());

            updateBranchProduct = branchProductRepo.save(branchProduct);

        }
        return updateBranchProduct;
    }

    @DeleteMapping("/branchProduct/{id}")
    public ResponseEntity<?> deleteBranchProduct(@PathVariable Long id){
        Optional<BranchProduct> branchProduct = branchProductRepo.findById(id);
        if (branchProduct.isPresent()){
            BranchProduct branchProduct1 = new BranchProduct();
            branchProduct1 = branchProduct.get();

            branchProductRepo.delete(branchProduct1);
        }

        return ResponseEntity.ok().build();
    }
}
