package com.pizzaria.project.repositories;

import com.pizzaria.project.entities.OrderMenu;
import com.pizzaria.project.entities.keys.OrderMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuKey> {

    OrderMenu findByOrdersId(Long Id);

}
