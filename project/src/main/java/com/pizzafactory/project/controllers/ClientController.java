package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Card;
import com.pizzafactory.project.entities.Client;
import com.pizzafactory.project.repositories.CardRepository;
import com.pizzafactory.project.repositories.ClientRepository;
import com.pizzafactory.project.repositories.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepo;
    private final OrdersRepository ordersRepo;
    private final CardRepository cardRepo;

    ClientController(ClientRepository  clientRepo, OrdersRepository ordersRepo, CardRepository cardRepo) {
        this.clientRepo = clientRepo;
        this.ordersRepo = ordersRepo;
        this.cardRepo=cardRepo;
    }

    @PostMapping("/card")
    public ResponseEntity<?> persistCard(String name, String telNum){
        Client client = clientRepo.findClientByTelNum(telNum);

        Card card = new Card(telNum);

        if(client == null){
            ResponseEntity.ok("No such client");
        }
        else if(client.getId() != null){
            card.setClient(client);
        }
        card = cardRepo.save(card);
        return ResponseEntity.ok("Card saved with id.");
    }

    @GetMapping("/find/card")
    public ResponseEntity<?> findCardByNum(String num){
        Card result = cardRepo.findCardByNum(num);
        if(result != null) {
            return ResponseEntity.ok(
                    "client: " + result.getClient().getFirstName() + ", " +
                    "card num: " + result.getNum() + ", " +
                    "id: " + result.getId());
        }
        return ResponseEntity.ok("Not found");
    }

    @GetMapping("/fetch/card")
    private List<Card> getAllCards(){ return cardRepo.findAll(); }

    @GetMapping("/fetch")
    private List<Client> getAllClients() { return clientRepo.findAll(); }

    @GetMapping("/find/name")
    public ResponseEntity<?> findClientByName(String fname, String lname) {
        List<Client> result = clientRepo.findClientByFirstNameAndLastName(fname, lname);
        return ResponseEntity.ok(result.isEmpty()?result:"Not found");
    }

    @PostMapping("/card/save")
    public List<Card> persistCard(String num, Client clientId){
        Card card = cardRepo.findCardByNum(num);
        List<Card> response= new ArrayList<>();
        if(card==null) {
            response.add(cardRepo.save(new Card()));
        }
        card.setNum(num);
        card.setClient(clientId);
        return response;
    }

    @PostMapping("/save")
    public List<Client> persistClient(
            String fname,
            String lname,
            String email,
            String num,
            String address,
            String username,
            String password,
            Integer bonusPoints,
            Boolean hasGiftCard
    ){
        List<Client> clients = clientRepo.findClientByFirstNameAndLastName(fname,lname);
        List<Client> response= new ArrayList<>();
        if(clients.isEmpty()){
            response.add(clientRepo.save((new Client(fname,lname,email,num,address,username,password,bonusPoints,hasGiftCard))));
        }
        for(Client client: clients){
            client.setTelNum(num);
            client.setAddress(address);
            client.setEmail(email);
            client.setBonusPoints(bonusPoints);
            client.setHasGiftCard(hasGiftCard);
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
