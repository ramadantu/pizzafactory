package com.pizzafactory.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "menu_drink",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private Set<Drink> drink;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<OrderMenu> orderMenus;

    public Menu() {
    }

    public Menu(String item, String description, Set<Drink> drink) {
        this.item = item;
        this.description = description;
        this.drink = drink;
    }

    public Long getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Drink> getDrink() {
        return drink;
    }

    public void setDrink(Set<Drink> drink) {
        this.drink = drink;
    }

    public Set<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(Set<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }
}
