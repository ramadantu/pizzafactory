package com.pizzafactory.project.entities;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String itemName;
    @Column
    private String description;
    @Column
    private String drink;

    public Menu(String itemName, String description, String drink) {
        this.itemName = itemName;
        this.description = description;
        this.drink = drink;
    }

    public Menu() {
    }

    public Long getId() {
        return Id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
