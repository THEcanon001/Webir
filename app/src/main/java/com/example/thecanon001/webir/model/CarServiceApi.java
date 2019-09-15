package com.example.thecanon001.webir.model;

import android.app.Application;
import android.util.Log;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.Car;
import com.example.thecanon001.webir.injection.BaseAplicattion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarServiceApi implements  CarService {
    private ArrayList<Car> listCar;

    @Inject
    WebServiceClient webServiceClient;

    @Override
    public ArrayList<Car> getCarList(Application application) {
        try {
            setUpDagger(application);
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

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
