package com.example.bai2.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DishService {

    public List<String> getAllCategories() {
        return Arrays.asList(
                "Món chính",
                "Đồ uống",
                "Tráng miệng",
                "Topping"
        );
    }
}

