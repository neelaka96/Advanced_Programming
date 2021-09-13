package com.jkwebservice.service.user.repos;

import com.jkwebservice.service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
