package com.example.bai3.controller;

import com.example.bai3.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bai3")
public class FoodControllerBai3 {
    private static final List<Food> foodList = new ArrayList<>();

    @ModelAttribute("categories")
    public List<String> categories() {
        return List.of("Khai vị", "Món chính", "Đồ uống", "Tráng miệng");
    }

    @GetMapping("/add")
    public String showForm() {
        return "bai3/add-food";
    }

    @PostMapping("/add")
    public String addFood(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile file,
            Model model
    )
    {
        if (file.isEmpty()) {
            model.addAttribute("error", "Vui lòng chọn ảnh!");
            return "bai3/add-food";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"))) {

            model.addAttribute("error", "Chỉ chấp nhận file ảnh (.jpg, .png, .jpeg)");
            return "bai3/add-food";
        }

        if (price < 0) {
            model.addAttribute("error", "Giá phải >= 0");
            return "bai3/add-food";
        }

        try {
            String uploadDir = "C:/RikkeiFood_Temp/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            String filePath = uploadDir + fileName;
            file.transferTo(new File(filePath));
            Food food = new Food(name, category, price, filePath);
            foodList.add(food);
            System.out.println("Đã thêm món: " + name);
            System.out.println("Tổng số món: " + foodList.size());
            model.addAttribute("success", "Thêm thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi upload file!");
        }

        return "bai3/add-food";
    }
}

