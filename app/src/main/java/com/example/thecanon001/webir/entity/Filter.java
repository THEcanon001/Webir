package com.example.thecanon001.webir.entity;

public class Filter {

    private Double price;
    private boolean _new;
    private boolean used;
    private boolean usd;
    private boolean $;
    private String brand;

    public Filter() {
    }

    public Filter(double price, boolean _new, boolean used, boolean usd, boolean $, String brand) {
        this.price = price;
        this._new = _new;
        this.used = used;
        this.usd = usd;
        this.$ = $;
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean is_new() {
        return _new;
    }

    public void set_new(boolean _new) {
        this._new = _new;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsd() {
        return usd;
    }

    public void setUsd(boolean usd) {
        this.usd = usd;
    }

    public boolean is$() {
        return $;
    }

    public void set$(boolean $) {
        this.$ = $;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
