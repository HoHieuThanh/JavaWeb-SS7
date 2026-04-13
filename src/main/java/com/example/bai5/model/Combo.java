package com.example.bai5.model;

import java.util.List;

public class Combo {
    private int id;
    private List<String> itemList;
    private String banner;

    public Combo() {}

    public Combo(int id, List<String> itemList, String banner) {
        this.id = id;
        this.itemList = itemList;
        this.banner = banner;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<String> getItemList() { return itemList; }
    public void setItemList(List<String> itemList) { this.itemList = itemList; }

    public String getBanner() { return banner; }
    public void setBanner(String banner) { this.banner = banner; }
}
