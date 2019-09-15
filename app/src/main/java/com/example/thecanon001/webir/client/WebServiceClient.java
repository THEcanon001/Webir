package com.example.thecanon001.webir.client;

import com.example.thecanon001.webir.entity.Car;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServiceClient {

    @GET("car")
    Call<List<Car>> gerCarList();
}
