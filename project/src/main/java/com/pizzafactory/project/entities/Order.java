package com.pizzafactory.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Timestamp createdAt;
    private Timestamp arrival;

    public Order() {
    }

    public Long getId() {
        return Id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getArrival() {
        return arrival;
    }

    public void setArrival(Timestamp arrival) {
        this.arrival = arrival;
    }
}
