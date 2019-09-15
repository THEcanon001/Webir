package com.example.thecanon001.webir.model;

import android.util.Log;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.Car;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CarServiceApi implements  CarService {
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private ArrayList<Car> listCar;
    private final static String BASE_URL = "localhost";

    @Override
    public ArrayList<Car> getCarList() {
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        try {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
            WebServiceClient webServiceClient = retrofit.create(WebServiceClient.class);
            Call<List<Car>> call = webServiceClient.gerCarList();
            call.enqueue(new Callback<List<Car>>() {
                @Override
                public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                    listCar = (ArrayList<Car>) response.body();
                }

                @Override
                public void onFailure(Call<List<Car>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
           e.printStackTrace();
           listCar = new ArrayList<>();
        }
        return listCar;
    }
}
