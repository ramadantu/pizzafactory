package com.pizzafactory.project.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private Long client_id;
    @Column
    private Long menu_id;
    @Column
    private Timestamp createdAt;
    @Column
    private Timestamp arrival;

    public Orders() {
    }

    public Orders(Timestamp createdAt, Timestamp arrival) {
        this.createdAt = createdAt;
        this.arrival = arrival;
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