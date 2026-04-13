package com.example.bai1.controller;

import com.example.bai1.model.RestaurantProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    @GetMapping("/merchant/profile")
    public String showForm(Model model) {
        model.addAttribute("profile", new RestaurantProfile());
        return "bai1/profile";
    }

    @PostMapping("/merchant/update-profile")
    public String updateProfile(RestaurantProfile profile) {
        System.out.println("Tên quán: " + profile.getName());
        return "success";
    }
}
