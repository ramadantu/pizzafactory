package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.OrderMenu;
import com.pizzafactory.project.entities.keys.OrderMenuKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuKey> {

    OrderMenu findByOrdersId(Long Id);

}
