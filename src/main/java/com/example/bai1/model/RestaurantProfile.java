package com.example.bai1.model;

public class RestaurantProfile {
    private String name;
    private String phone;
    private boolean active;

    public RestaurantProfile(String name, String phone, boolean active) {
        this.name = name;
        this.phone = phone;
        this.active = active;
    }

    public RestaurantProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}