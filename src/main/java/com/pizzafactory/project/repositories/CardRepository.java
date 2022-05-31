package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findCardByClientEmail(String email);
}
