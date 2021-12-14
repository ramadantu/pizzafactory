package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pizzafactory.project.entities.keys.OrderMenuKey;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class OrderMenu {   // свързочна таблица
    @EmbeddedId
    private OrderMenuKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name ="order_id")
    private Orders order;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name ="menu_id")
    private Menu menu;

    private Integer quantity;

    public OrderMenu() {
    }

    public OrderMenu(OrderMenuKey id, Orders order, Menu menu, Integer quantity) {
        this.id = id;
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
    }

    public OrderMenuKey getId() {
        return id;
    }

    public Orders getOrder() { return order; }

    public void setOrder(Orders order) { this.order = order; }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQuality() {
        return quantity;
    }

    public void setQuality(Integer quality) {
        this.quantity = quantity;
    }

}
