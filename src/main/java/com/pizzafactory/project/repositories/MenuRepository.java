package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findMenuByItem(String item);

    List<Menu> findMenuByItemAndDescription(String itemName, String description);

    @Query("SELECT m " +
            "FROM Menu m " +
            "WHERE lower(m.item) " +
            "LIKE :#{#item == null || #item.isEmpty()? '%' : #item + '%'} ")
    Page<Menu> filterMenus(Pageable pageable, String item);

}
