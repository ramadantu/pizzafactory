package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findMenuByItem (String item);

}
