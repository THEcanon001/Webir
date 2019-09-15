package com.example.thecanon001.webir.model;

import android.app.Application;

import com.example.thecanon001.webir.entity.Car;

import java.util.ArrayList;

public interface CarService {
    public ArrayList<Car> getCarList(Application application);

    ArrayList<Car> getCarList(Application application, String filter);
}
