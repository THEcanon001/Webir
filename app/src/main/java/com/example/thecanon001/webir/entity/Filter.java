package com.example.thecanon001.webir.entity;

public class Filter {
    private String type;
    private String value;

    public Filter() {
    }

    public Filter(String type, String value) {
        this.type = type;
        this.value = value;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
