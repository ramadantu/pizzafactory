package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Client;
import com.pizzafactory.project.repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController {

    private ClientRepository clientRepo;

    ClientController(ClientRepository  clientRepository) {
        clientRepo = clientRepository;
    }

    @GetMapping("/fetch")
    private List<Client> getAllClients() { return clientRepo.findAll(); }

    @GetMapping("/find/name")
    public ResponseEntity<?> findClientByName(String fname, String lname) {
        Optional<Client> result = clientRepo.findClientByFirstNameAndLastName(fname, lname);
        return result.isPresent()?ResponseEntity.ok(result.get()) : ResponseEntity.ok("Not found!");
    }

}
