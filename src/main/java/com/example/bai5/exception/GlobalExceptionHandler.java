package com.example.bai5.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public String handleFileTooLarge(IllegalStateException ex, Model model) {

        if (ex.getMessage() != null && ex.getMessage().contains("exceeds")) {
            model.addAttribute("error", "File quá lớn! Tối đa 10MB");
            return "bai5/error";
        }

        model.addAttribute("error", "Lỗi hệ thống!");
        return "bai5/error";
    }
}
