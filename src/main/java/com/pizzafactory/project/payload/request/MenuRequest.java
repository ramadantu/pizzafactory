package com.pizzafactory.project.payload.request;

import java.util.Set;

public class MenuRequest {

    private String itemName;
    private String description;
    private Set<String> drinks;

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

    public Set<String> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<String> drinks) {
        this.drinks = drinks;
    }
}
