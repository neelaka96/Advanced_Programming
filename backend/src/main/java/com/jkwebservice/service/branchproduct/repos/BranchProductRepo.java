package com.jkwebservice.service.branchproduct.repos;

import com.jkwebservice.service.models.BranchProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchProductRepo extends JpaRepository<BranchProduct, Long> {
}
