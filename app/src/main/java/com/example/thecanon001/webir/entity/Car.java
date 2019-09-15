package com.example.thecanon001.webir.entity;

import java.io.Serializable;

public class Car implements Serializable {
    private String image;
    private String model;
    private String km;
    private String price;
    private String location;

    public Car() {
    }

    public Car(String image, String model, String km, String price, String location) {
        this.image = image;
        this.model = model;
        this.km = km;
        this.price = price;
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
