package com.example.thecanon001.webir.model;

import android.app.Application;
import android.util.Log;

import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.Filter;
import com.example.thecanon001.webir.entity.Vehicle;
import com.example.thecanon001.webir.injection.BaseAplicattion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarServiceApi implements  CarService {
    private ArrayList<Vehicle> listVehicle;

    @Inject
    WebServiceClient webServiceClient;

    @Override
    public void getCarList(Application application, CarViewAdapter cardViewAdapter) {
        listVehicle = new ArrayList<>();
        try {
            setUpDagger(application);
            Call<List<Vehicle>> call = webServiceClient.gerCarList("title", "");
            call.enqueue(new Callback<List<Vehicle>>() {
                @Override
                public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                    listVehicle = (ArrayList<Vehicle>) response.body();
                    cardViewAdapter.updateListView(listVehicle);
                }

                @Override
                public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
           e.printStackTrace();
        }
    }

    @Override
    public void getCarList(Application application, Filter filter, CarViewAdapter cardViewAdapter) {
        listVehicle = new ArrayList<>();
        try {
            setUpDagger(application);
            Call<List<Vehicle>> call = webServiceClient.gerCarList("title", filter.getBrand());
            call.enqueue(new Callback<List<Vehicle>>() {
                @Override
                public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                    listVehicle = (ArrayList<Vehicle>) response.body();
                    cardViewAdapter.updateListView(listVehicle);
                }

                @Override
                public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
