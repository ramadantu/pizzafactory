package com.pizzaria.project.repositories;

import com.pizzaria.project.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findCardByClientEmail(String email);
}
