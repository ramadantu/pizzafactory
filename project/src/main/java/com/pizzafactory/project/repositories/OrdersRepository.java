package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
