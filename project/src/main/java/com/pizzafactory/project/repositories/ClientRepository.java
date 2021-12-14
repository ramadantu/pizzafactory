package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();

    List<Client> findClientByFirstNameAndLastName(String fname, String lname);

    Client findClientByTelNum(String telNum);

}
