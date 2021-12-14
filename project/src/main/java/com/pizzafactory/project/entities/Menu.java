package com.pizzafactory.project.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String itemName;
    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "menu_drink",
            joinColumns= @JoinColumn(name="menu_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private Set<Drink> drink;

    public Menu(String itemName, String description, Set<Drink> drink) {
        this.itemName = itemName;
        this.description = description;
        this.drink=drink;
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

    public Set<Drink> getDrink() {
        return drink;
    }

    public void setDrink(Set<Drink> drink) {
        this.drink = drink;
    }
}
