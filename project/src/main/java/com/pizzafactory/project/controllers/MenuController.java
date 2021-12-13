package com.pizzafactory.project.controllers;

import com.pizzafactory.project.entities.Menu;
import com.pizzafactory.project.repositories.MenuRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    public MenuRepository menuRepo;

    public MenuController(MenuRepository menuRepository) {
        menuRepo = menuRepository;
    }

    @GetMapping("/fetch")
    private List<Menu> getItems() {return menuRepo.findAll();}
}
