package com.example.thecanon001.webir.entity;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String id;
    private String title;
    private int price;
    private String currency;
    private String condition;
    private String photos;
    private String brand;
    private int kms;
    private int year;

    public Vehicle() {
    }

    public Vehicle(String id, String title, int price, String currency, String condition, String photos, String brand, int kms, int year) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currency = currency;
        this.condition = condition;
        this.photos = photos;
        this.brand = brand;
        this.kms = kms;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
