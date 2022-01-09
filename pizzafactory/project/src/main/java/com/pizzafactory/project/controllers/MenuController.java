package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.*;
import com.pizzafactory.project.payload.request.MenuRequest;
import com.pizzafactory.project.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepo;
    private final DrinkRepository drinkRepo;


    MenuController(MenuRepository menuRepo,
                   DrinkRepository drinkRepo){
        this.menuRepo=menuRepo;
        this.drinkRepo=drinkRepo;
    }

    @GetMapping("/fetch")
    private List<Menu> getAllItems() {return menuRepo.findAll();}

    @PostMapping("/save/drink")
    public ResponseEntity<?> persistDrink(String name) {
        Drink drink= drinkRepo.findDrinkByName(name);
        if(drink!=null){
            return ResponseEntity.ok("Drink already exist.");
        }
        return ResponseEntity.ok("Drink " + drinkRepo.save(new Drink(name)).getName()+" is saved.");
    }
    @PostMapping("/save")
    public ResponseEntity<?> persistMenu(@RequestBody MenuRequest menuRequest){
        Set<Drink> drinkSet= new HashSet<>();
        for(String drinkName: menuRequest.getDrinks()){
            Drink drink=drinkRepo.findDrinkByName(drinkName);
            drinkSet.add(drink);
        }
        return ResponseEntity.ok("Menu item " + menuRepo.save(new Menu(menuRequest.getItemName(),menuRequest.getDescription(), drinkSet)).getItem()+ " is saved.");

    }

}
