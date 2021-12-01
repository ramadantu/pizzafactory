package com.pizzafactory.project.controllers;

import com.pizzafactory.project.repositories.OrderRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderContoller {

    private OrderRepository orderRepo;

    public OrderContoller(OrderRepository orderRepository) {
        orderRepo = orderRepository;
    }
}
