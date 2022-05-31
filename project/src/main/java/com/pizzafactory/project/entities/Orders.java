package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "orders")
    private Set<OrderMenu> orderMenus;

    private Timestamp createdAt;
    private Timestamp arrival;

    public Orders() {
    }

    public Orders(Client client, Timestamp createdAt, Timestamp arrival) {
        this.client = client;
        this.createdAt = createdAt;
        this.arrival = arrival;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Set<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(Set<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }
}