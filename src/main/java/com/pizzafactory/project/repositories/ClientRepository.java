package com.pizzafactory.project.repositories;

import com.pizzafactory.project.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findClientByFirstNameAndLastName(String fname, String lname);

    List<Client> findClientByLastName(String lname);

    Client findClientByEmail(String email);

    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE lower(c.firstName) " +
            "LIKE :#{#firstName == null || #firstName.isEmpty()? '%' : #firstName + '%'} " +
            "AND lower(c.lastName) " +
            "LIKE :#{#lastName == null || #lastName.isEmpty()? '%' : #lastName + '%'}")
    Page<Client> filterClients(Pageable pageable, String firstName, String lastName);

}
