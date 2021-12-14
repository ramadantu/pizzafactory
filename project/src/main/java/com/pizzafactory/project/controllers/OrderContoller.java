package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Client;
import com.pizzafactory.project.entities.Orders;
import com.pizzafactory.project.repositories.ClientRepository;
import com.pizzafactory.project.repositories.OrdersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderContoller {

    private final OrdersRepository orderRepo;
    private final ClientRepository clientRepo;

    public OrderContoller(OrdersRepository orderRepo,
                          ClientRepository clientRepo) {
        this.orderRepo = orderRepo;
        this.clientRepo = clientRepo;
    }

    @GetMapping("/fetch")
    private List<Orders> getAllOrders() {return orderRepo.findAll();}

}
