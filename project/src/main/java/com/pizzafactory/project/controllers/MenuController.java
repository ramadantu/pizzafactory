package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.*;
import com.pizzafactory.project.entities.keys.OrderMenuKey;
import com.pizzafactory.project.payload.request.MenuRequest;
import com.pizzafactory.project.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepo;
    private final DrinkRepository drinkRepo;
    private final ClientRepository clientRepo;
    private final OrderMenuRepository orderMenuRepo;
    private final OrdersRepository ordersRepo;


    MenuController(MenuRepository menuRepo,
                   DrinkRepository drinkRepo,
                   ClientRepository clientRepo,
                   OrderMenuRepository orderMenuRepo,
                   OrdersRepository ordersRepo){
        this.menuRepo=menuRepo;
        this.drinkRepo=drinkRepo;
        this.clientRepo=clientRepo;
        this.orderMenuRepo=orderMenuRepo;
        this.ordersRepo=ordersRepo;
    }

    @GetMapping("all/orders")
    public List<Orders> getAllOrders(){
        return ordersRepo.findAll();
    }

    @PostMapping("/save/drink")
    public ResponseEntity<?> persistDrink(String name) {
        Drink drink= drinkRepo.findDrinkByName(name);
        if(drink!=null){
            return ResponseEntity.ok("Drink already exist.");
        }
        return ResponseEntity.ok("Drink " + drinkRepo.save(new Drink(name)).getName()+" is saved.");
    }
    @PostMapping("/save/menu")
    public ResponseEntity<?> persistMenu(@RequestBody MenuRequest menuRequest){
        Set<Drink> drinkSet= new HashSet<>();
        for(String drinkName: menuRequest.getDrinks()){
            Drink drink=drinkRepo.findDrinkByName(drinkName);
            drinkSet.add(drink);
        }
        return ResponseEntity.ok("Menu item " + menuRepo.save(new Menu(menuRequest.getItemName(),menuRequest.getDescription(), drinkSet)).getItemName()+ " is saved.");

    }
    @PostMapping("/save/order")
    public ResponseEntity<?> persistOrder(String menuItem, String telNum, int quantity){
        Menu dbMenuItem = menuRepo.findMenuByItemName(menuItem);
        Client client = clientRepo.findClientByTelNum(telNum);
        Orders newOrder = ordersRepo.save(new Orders(client,new Timestamp(System.currentTimeMillis())));

        OrderMenu orderMenu = orderMenuRepo.save(new OrderMenu(
                new OrderMenuKey(newOrder.getId(), dbMenuItem.getId()),
                newOrder,
                dbMenuItem,
                quantity
        ));
        return ResponseEntity.ok("New order placed with id - " + orderMenu.getId() + " !");
    }

}
