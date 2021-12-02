package com.pizzafactory.project.controllers;

import com.pizzafactory.project.repositories.MenuRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    public MenuRepository menuRepo;

    public MenuController(MenuRepository menuRepository) {
        menuRepo = menuRepository;
    }
}
