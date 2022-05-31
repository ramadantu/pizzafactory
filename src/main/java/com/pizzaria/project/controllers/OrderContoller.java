package com.pizzaria.project.controllers;

import com.pizzaria.project.entities.Client;
import com.pizzaria.project.entities.Menu;
import com.pizzaria.project.entities.OrderMenu;
import com.pizzaria.project.entities.Orders;
import com.pizzaria.project.entities.keys.OrderMenuKey;
import com.pizzaria.project.repositories.ClientRepository;
import com.pizzaria.project.repositories.MenuRepository;
import com.pizzaria.project.repositories.OrderMenuRepository;
import com.pizzaria.project.repositories.OrdersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderContoller {

    private final OrdersRepository ordersRepo;
    private final ClientRepository clientRepo;
    private final MenuRepository menuRepo;
    private final OrderMenuRepository orderMenuRepo;

    public OrderContoller(OrdersRepository ordersRepo,
                          ClientRepository clientRepo,
                          MenuRepository menuRepo,
                          OrderMenuRepository orderMenuRepo) {
        this.ordersRepo = ordersRepo;
        this.clientRepo = clientRepo;
        this.menuRepo = menuRepo;
        this.orderMenuRepo = orderMenuRepo;
    }

    @GetMapping("/fetch")
    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistOrder(String menuItem, String email, int quantity) {

        if (quantity <= 0)
            return ResponseEntity.ok("Enter a valid quantity!");

        Menu dbMenuItem = menuRepo.findMenuByItem(menuItem);
        if (dbMenuItem == null)
            return ResponseEntity.ok("Enter an item from the menu!");

        Client client = clientRepo.findClientByEmail(email);
        if (client == null)
            return ResponseEntity.ok("Enter a valid client email!");

        Orders newOrder = ordersRepo.save(
                new Orders(
                        client,
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis() + 2400000)
                ));

        orderMenuRepo.save(new OrderMenu(
                new OrderMenuKey(newOrder.getId(), dbMenuItem.getId()),
                newOrder,
                dbMenuItem,
                quantity
        ));

        return ResponseEntity.ok("New order placed with id - " + newOrder.getId() + " !");
    }

}

