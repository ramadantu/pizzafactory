package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
