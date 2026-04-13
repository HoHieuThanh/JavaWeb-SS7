package com.example.bai2.controller;

import com.example.bai2.model.Dish;
import com.example.bai2.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bai2")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }
    @ModelAttribute("categories")
    public List<String> categories() {
        return dishService.getAllCategories();
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("dish", new Dish());
        return "bai2/add-dish";
    }
    @GetMapping("/edit")
    public String showEditForm(Model model) {
        model.addAttribute("dish", new Dish("Cơm", "Món chính"));
        return "bai2/edit-dish";
    }
    @GetMapping("/search")
    public String showSearchForm() {
        return "bai2/search-dish";
    }
}

