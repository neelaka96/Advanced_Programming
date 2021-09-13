package com.jkwebservice.service.transport.contollers;

import com.jkwebservice.service.models.Transport;
import com.jkwebservice.service.transport.repos.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransportController {

    @Autowired
    private TransportRepo transportRepo;
    private Integer finalResult;

    @GetMapping("/transport/{id}")
    public Transport getTransport(@PathVariable Long id) {

        Transport transport = new Transport();

        Optional<Transport> details = transportRepo.findById(id);
        if (details.isPresent()) {
            transport = details.get();
        }

        return transport;
    }

    @GetMapping("/transport")
    public List<Transport> getTransport() {

        return transportRepo.findAll();
    }

    @PostMapping("/transport")
    public Transport addTransport(@RequestBody Transport transport){

        return transportRepo.save(transport);

    }

    @PutMapping("/transport/{id}")
    public Transport updateTransport(@PathVariable Long id, @RequestBody Transport transportData){


        Transport updatedTransport = new Transport();

        Optional<Transport> details = transportRepo.findById(id);
        if (details.isPresent()){
            Transport transport = new Transport();
            transport = details.get();

            transport.setId(transportData.getId());
            transport.setDate(transportData.getDate());
            transport.setStatus(transportData.getStatus());
            transport.setVehicleId(transportData.getVehicleId());
            transport.setRequestId(transportData.getRequestId());

            updatedTransport = transportRepo.save(transport);

        }
        return updatedTransport;
    }

    @DeleteMapping("/transport/{id}")
    public ResponseEntity<?> deleteTransport(@PathVariable Long id){
        Optional<Transport> transport = transportRepo.findById(id);
        if (transport.isPresent()){
            Transport transport1 = new Transport();
            transport1 = transport.get();

            transportRepo.delete(transport1);
        }

        return ResponseEntity.ok().build();
    }
}
