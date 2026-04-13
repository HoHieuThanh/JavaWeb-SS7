package com.example.btth.controller;

import com.example.btth.model.dto.DailyDishDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/daily")
public class DailyController {

    @ModelAttribute("categories")
    public List<String> getCategories() {
        return Arrays.asList("Điểm tâm", "Bữa trưa", "Ăn vặt", "Đồ uống");
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("dailyDish", new DailyDishDTO());
        return "btth/daily-special";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("dailyDish") DailyDishDTO dto,
            @RequestParam("bannerFile") MultipartFile file,
            RedirectAttributes redirect
    ) {

        if (file.isEmpty()) {
            redirect.addFlashAttribute("error", "Chưa chọn ảnh");
            return "redirect:/daily/form";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"))) {

            redirect.addFlashAttribute("error", "Chỉ chấp nhận ảnh jpg/png/jpeg");
            return "redirect:/daily/form";
        }

        try {
            String uploadDir = "C:/RikkeiFood_Temp/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String newFileName = System.currentTimeMillis() + "_" + fileName;
            file.transferTo(new File(uploadDir + newFileName));

            System.out.println("Đã nhận món: " + dto.getName());
            System.out.println("Danh mục: " + dto.getCategory());
            System.out.println("Upload ảnh thành công: " + newFileName);

            redirect.addFlashAttribute("success", "Thêm món thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Lỗi upload!");
            return "redirect:/daily/form";
        }

        return "redirect:/daily/success";
    }

    @GetMapping("/success")
    public String success() {
        return "btth/daily-success";
    }
}

