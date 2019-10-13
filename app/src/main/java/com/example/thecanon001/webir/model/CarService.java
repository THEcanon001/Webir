package com.example.thecanon001.webir.model;

import android.app.Application;

import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.entity.Filter;


public interface CarService {
    void getCarList(Application application, CarViewAdapter cardViewAdapter);

    void getCarList(Application application, Filter filter, CarViewAdapter cardViewAdapter);
}
