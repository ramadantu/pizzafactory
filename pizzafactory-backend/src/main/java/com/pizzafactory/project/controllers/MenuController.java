package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Menu;
import com.pizzafactory.project.entities.Drink;
import com.pizzafactory.project.payload.request.MenuRequest;
import com.pizzafactory.project.repositories.DrinkRepository;
import com.pizzafactory.project.repositories.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepo;
    private final DrinkRepository drinkRepo;


    MenuController(MenuRepository menuRepo,
                   DrinkRepository drinkRepo) {
        this.menuRepo = menuRepo;
        this.drinkRepo = drinkRepo;
    }

    @GetMapping("/fetch")
    public List<String> fetchMenusItem() {
        List<String> menuItem = new ArrayList<>();
        for (Menu menu: menuRepo.findAll()) {
            menuItem.add(menu.getItem());
        }
        return menuItem;
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterMenus(@RequestParam(defaultValue = "") String item,
                                         @RequestParam(defaultValue = "1") int currentPage,
                                         @RequestParam(defaultValue = "5") int perPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Menu> menus = menuRepo.filterMenus(pageable, item.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("totalElements", menus.getTotalElements());
        response.put("totalPages", menus.getTotalPages());
        response.put("menus", menus.getContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/drink/fetch")
    private List<Drink> getAllDrinks() {
        return drinkRepo.findAll();
    }

    @PostMapping("/save/drink")
    public ResponseEntity<?> persistDrink(String name) {
        Drink drink = drinkRepo.findDrinkByName(name);
        if (drink != null) {
            return ResponseEntity.ok("Drink already exist.");
        }
        return ResponseEntity.ok("Drink " + drinkRepo.save(new Drink(name)).getName() + " is saved.");
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistMenu(@RequestBody MenuRequest menuRequest) {

        Set<Drink> drinkSet = new HashSet<>();
        List<Menu> menuExist = menuRepo.findMenuByItemAndDescription(menuRequest.getItemName(), menuRequest.getDescription());

        for (String drinkName : menuRequest.getDrinks()) {
            Drink drink = drinkRepo.findDrinkByName(drinkName);

            if (drink == null)
                return ResponseEntity.ok("Choose drinks from the menu!");
            else {
                drinkSet.add(drink);
            }
        }

        if (menuExist.isEmpty())
            return ResponseEntity.ok(
                    "Menu item " +
                            menuRepo.save(new Menu(menuRequest.getItemName(), menuRequest.getDescription(), drinkSet)).getItem() +
                            " is saved.");
        else
            return ResponseEntity.ok("Menu already exists!");
    }

}
