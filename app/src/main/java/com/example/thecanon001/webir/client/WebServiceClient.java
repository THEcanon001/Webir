package com.example.thecanon001.webir.client;

import com.example.thecanon001.webir.entity.Vehicle;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServiceClient {

    @GET("vehicles")
    Call<List<Vehicle>> gerCarList(@Query("filter_type") String fylter_type, @Query("filter") String filter);

    @GET("vehicles_filters")
    Call<List<Vehicle>> gerCarList(@Query("filters") String filters);

    @GET("vehicles_all")
    Call<List<Vehicle>> gerCarList();
}
