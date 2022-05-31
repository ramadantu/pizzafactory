package com.pizzaria.project.repositories;

import com.pizzaria.project.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

    Drink findDrinkByName(String name);
}
