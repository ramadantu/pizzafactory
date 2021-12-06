package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Client;
import com.pizzafactory.project.repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController {

    private ClientRepository clientRepo;

    ClientController(ClientRepository  clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping("/fetch")
    private List<Client> getAllClients() { return clientRepo.findAll(); }

    @GetMapping("/find/name")
    public ResponseEntity<?> findClientByName(String fname, String lname) {
        List<Client> result = clientRepo.findClientByFirstNameAndLastName(fname, lname);
        return ResponseEntity.ok(result.isEmpty()?result:"Not found");
    }
    @PostMapping("/save")
    public List<Client> persistClient(String fname, String lname,String email, String num,String address, String username, String password){
        List<Client> clients = clientRepo.findClientByFirstNameAndLastName(fname,lname);
        List<Client> response= new ArrayList<>();
        if(clients.isEmpty()){
            response.add(clientRepo.save((new Client(fname,lname,email,num,address,username,password))));
        }
        for(Client client: clients){
            client.setTelNum(num);
            client.setAddress(address);
            client.setEmail(email);
            response.add(clientRepo.save(client));
        }
        return response;
    }
    @DeleteMapping("/delete")
    public String deleteClient(String fname, String lname){
        List<Client> result = clientRepo.findClientByFirstNameAndLastName(fname, lname);
        if(result.isEmpty()) {
            return "Client not found";
        }
        for(Client client:result) {
            clientRepo.delete(client);
        }
        return fname+" "+lname+" deleted";
    }
}
