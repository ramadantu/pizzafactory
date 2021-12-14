package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private Long Id;

    @Column
    private Long client_id;
    @Column
    private Long menu_id;
    @Column
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false,updatable = false)
    private Client client;

    @OneToMany(mappedBy = "order")
    private Set<OrderMenu> orders;

    public Orders() {
    }

    public Orders(Client client,Timestamp createdAt) {
        this.client=client;
        this.createdAt = createdAt;
    }

    public Long getClient_id() {
        return client_id;
    }

    public Long getMenu_id() {
        return menu_id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderMenu> getOrders() {
        return orders;
    }

}