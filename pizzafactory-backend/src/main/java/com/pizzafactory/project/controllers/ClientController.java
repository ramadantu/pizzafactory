package com.pizzafactory.project.controllers;

import com.pizzafactory.project.repositories.CardRepository;
import com.pizzafactory.project.repositories.ClientRepository;
import com.pizzafactory.project.repositories.OrdersRepository;
import com.pizzafactory.project.entities.Card;
import com.pizzafactory.project.entities.Client;
import com.pizzafactory.project.entities.OrderMenu;
import com.pizzafactory.project.entities.Orders;
import com.pizzafactory.project.repositories.OrderMenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepo;
    private final CardRepository cardRepo;
    private final OrdersRepository ordersRepo;
    private final OrderMenuRepository orderMenuRepo;

    ClientController(
            ClientRepository clientRepo,
            CardRepository cardRepo,
            OrdersRepository ordersRepo,
            OrderMenuRepository orderMenuRepo) {
        this.clientRepo = clientRepo;
        this.cardRepo = cardRepo;
        this.ordersRepo = ordersRepo;
        this.orderMenuRepo = orderMenuRepo;
    }

    @PostMapping("/card/save")
    public ResponseEntity<?> persistCard(String name, String email) {
        Client client = clientRepo.findClientByEmail(email);
        if (client == null || !name.equals(client.getFirstName()))
            return ResponseEntity.ok("Enter a valid client!");

        Card card = cardRepo.findCardByClientEmail(email);
        if (card == null)
            card = new Card(email);
        else
            return ResponseEntity.ok("This client already has a card!");

        if (client.getId() != null) {
            card.setClientEmail(client.getEmail());
            card.setClient(client);
        }
        card = cardRepo.save(card);
        return ResponseEntity.ok("Card saved with id " + card.getId());
    }

    @GetMapping("/card/find")
    public ResponseEntity<?> findCardByClientEmail(String email) {
        Card result = cardRepo.findCardByClientEmail(email);
        if (result != null) {
            return ResponseEntity.ok(
                    "client email: " + result.getClientEmail() + ", " +
                            "card id: " + result.getId());
        }
        return ResponseEntity.ok("Not found");
    }

    @GetMapping("/card/fetch")
    private List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterClients(String fname, String lname, int currentPage, int perPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Client> clients = clientRepo.filterClients(pageable, fname.toLowerCase(), lname.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("totalElements", clients.getTotalElements());
        response.put("totalPages", clients.getTotalPages());
        response.put("clients", clients.getContent());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public List<Client> persistClient(
            @RequestParam(defaultValue = "") String firstName,
            @RequestParam(defaultValue = "") String lastName,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String num,
            @RequestParam(defaultValue = "") String address,
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String password) {

        List<Client> clients = clientRepo.findClientByLastName(lastName);
        List<Client> response = new ArrayList<>();

        if (clients.isEmpty()) {
            response.add(clientRepo.save((new Client(firstName, lastName, email, num, address, username, password))));
        }
        for (Client client : clients) {
            client.setPassword(password);
            client.setUsername(username);
            client.setTelNum(num);
            client.setAddress(address);
            client.setEmail(email);
            response.add(clientRepo.save(client));
        }
        return response;
    }

    @DeleteMapping("/delete")
    public String deleteClient(String fname, String lname) {
        List<Client> result = clientRepo.findClientByFirstNameAndLastName(fname, lname);

        if (result.isEmpty()) {
            return "Client not found";
        }

        for (Client client : result) {
            Card card = cardRepo.findCardByClientEmail(client.getEmail());
            if (card != null)
                cardRepo.delete(card);
            Orders order = ordersRepo.findOrdersByClient(client);
            if (order != null) {
                OrderMenu orderMenu = orderMenuRepo.findByOrdersId(order.getId());
                if (orderMenu != null)
                    orderMenuRepo.delete(orderMenu);
                ordersRepo.delete(order);
            }
            clientRepo.delete(client);
        }
        return fname + " " + lname + " was deleted";
    }
}
