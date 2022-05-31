package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.*;
import com.pizzafactory.project.entities.keys.OrderMenuKey;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Entity
public class OrderMenu {
    @EmbeddedId
    private OrderMenuKey id;


    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orders_id")
    @JsonBackReference
    private Orders orders;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private Integer quantity;

    public OrderMenu() {
    }

    public OrderMenu(OrderMenuKey id, Orders orders, Menu menu, Integer quantity) {
        this.id = id;
        this.orders = orders;
        this.menu = menu;
        this.quantity = quantity;
    }

    public OrderMenuKey getId() {
        return id;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
