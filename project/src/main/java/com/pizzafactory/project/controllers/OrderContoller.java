package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Orders;
import com.pizzafactory.project.repositories.OrdersRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderContoller {

    private OrdersRepository orderRepo;

    public OrderContoller(OrdersRepository orderRepository) {
        orderRepo = orderRepository;
    }

    @GetMapping("/fetch")
    private List<Orders> getAllOrders() {return orderRepo.findAll();}

}
