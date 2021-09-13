package com.jkwebservice.service.branch.repos;

import com.jkwebservice.service.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepo extends JpaRepository<Branch, Long> {
}
