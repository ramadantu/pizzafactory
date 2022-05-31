package com.pizzaria.project.repositories;

import com.pizzaria.project.entities.Client;
import com.pizzaria.project.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findOrdersByClient(Client client);

}
