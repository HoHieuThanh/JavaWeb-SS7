package com.example.bai4.controller;

import com.example.bai4.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bai4")
public class FoodControllerBai4 {

    private static final List<Food> foodList = new ArrayList<>();
    private static int idCounter = 1;

    @ModelAttribute("categories")
    public List<String> categories() {
        return List.of("Khai vị", "Món chính", "Đồ uống", "Tráng miệng");
    }

    @GetMapping("/add")
    public String showForm() {
        return "bai4/add-food";
    }

    @PostMapping("/add")
    public String addFood(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng chọn ảnh!");
            return "redirect:/bai4/add";
        }

        if (price < 0) {
            redirectAttributes.addFlashAttribute("error", "Giá phải >= 0");
            return "redirect:/bai4/add";
        }

        try {
            String uploadDir = "C:/RikkeiFood_Temp/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String original = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "_" + original;
            File dest = new File(uploadDir + newFileName);
            file.transferTo(dest);
            Food food = new Food(
                    idCounter++,
                    name,
                    category,
                    price,
                    newFileName
            );

            foodList.add(food);

            redirectAttributes.addFlashAttribute("success", "Thêm thành công!");

            return "redirect:/bai4/detail?id=" + food.getId();

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi upload!");
            return "redirect:/bai4/add";
        }
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        Food food = foodList.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("food", food);
        return "bai4/food-detail";
    }
}
