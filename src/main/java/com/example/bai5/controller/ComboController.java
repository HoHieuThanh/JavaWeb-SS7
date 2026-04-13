package com.example.bai5.controller;

import com.example.bai5.model.Combo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bai5")
public class ComboController {

    private static final List<Combo> comboList = new ArrayList<>();
    private static int idCounter = 1;

    @ModelAttribute("foods")
    public List<String> foods() {
        return List.of("Phở", "Bún", "Cơm", "Trà sữa", "Bánh mì");
    }

    @GetMapping("/add")
    public String showForm() {
        return "bai5/add-combo";
    }

    @PostMapping("/add")
    public String addCombo(
            @RequestParam(value = "items", required = false) List<String> items,
            @RequestParam("banner") MultipartFile file,
            Model model
    ) {

        if (items == null || items.size() < 2) {
            model.addAttribute("error", "Combo phải có ít nhất 2 món!");
            return "bai5/add-combo";
        }

        if (file.isEmpty()) {
            model.addAttribute("error", "Vui lòng chọn banner!");
            return "bai5/add-combo";
        }

        try {
            String uploadDir = "C:/RikkeiFood_Temp/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));

            Combo combo = new Combo(idCounter++, items, fileName);
            comboList.add(combo);

            System.out.println("=== COMBO MỚI ===");
            System.out.println("{");
            System.out.println("  id: " + combo.getId() + ",");
            System.out.println("  items: " + combo.getItemList() + ",");
            System.out.println("  banner: '" + combo.getBanner() + "'");
            System.out.println("}");

            model.addAttribute("success", "Tạo combo thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi upload!");
        }

        return "bai5/add-combo";
    }
}
