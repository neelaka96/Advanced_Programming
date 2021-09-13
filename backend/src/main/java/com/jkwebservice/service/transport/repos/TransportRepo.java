package com.jkwebservice.service.transport.repos;

import com.jkwebservice.service.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepo extends JpaRepository<Transport, Long> {
}
