package com.jkwebservice.service.product.contollers;

import com.jkwebservice.service.models.Product;
import com.jkwebservice.service.product.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    private Integer finalResult;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {

        Product product = new Product();

        Optional<Product> details = productRepo.findById(id);
        if (details.isPresent()) {
            product = details.get();
        }

        return product;
    }

    @GetMapping("/product")
    public List<Product> getProducts() {

        return productRepo.findAll();
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){

        return productRepo.save(product);

    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productData){


        Product updatedproduct = new Product();

        Optional<Product> details = productRepo.findById(id);
        if (details.isPresent()){
            Product product = new Product();
            product = details.get();

            product.setId(productData.getId());
            product.setName(productData.getName());
            product.setProductCode(product.getProductCode());
            product.setUnitPrice(product.getUnitPrice());

            updatedproduct = productRepo.save(product);

        }
        return updatedproduct;
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()){
            Product product1 = new Product();
            product1 = product.get();

            productRepo.delete(product1);
        }

        return ResponseEntity.ok().build();
    }
}
