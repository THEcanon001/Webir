package com.example.thecanon001.webir.model;

import android.app.Application;

import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.entity.Filter;
import com.example.thecanon001.webir.entity.Vehicle;
import com.example.thecanon001.webir.injection.BaseAplicattion;

import java.util.ArrayList;

import javax.inject.Inject;

public class CarServiceStub implements CarService {

    @Inject
    ArrayList<Vehicle> listVehicle;

    @Override
    public void getCarList(Application application, CarViewAdapter cardViewAdapter) {
        setUpDagger(application);
        listVehicle.get(0).setPhotos(application.getString(R.string.image0));
        listVehicle.get(1).setPhotos(application.getString(R.string.image1));
        listVehicle.get(2).setPhotos(application.getString(R.string.image2));
        listVehicle.get(3).setPhotos(application.getString(R.string.image3));
        listVehicle.get(4).setPhotos(application.getString(R.string.image4));
        listVehicle.get(5).setPhotos(application.getString(R.string.image5));
        listVehicle.get(6).setPhotos(application.getString(R.string.image6));
        listVehicle.get(7).setPhotos(application.getString(R.string.image7));
        listVehicle.get(8).setPhotos(application.getString(R.string.image8));
        listVehicle.get(9).setPhotos(application.getString(R.string.image9));
        listVehicle.get(10).setPhotos(application.getString(R.string.image10));
        cardViewAdapter.updateListView(listVehicle);
    }

    @Override
    public void getCarList(Application application, Filter filter, CarViewAdapter cardViewAdapter) {
        ArrayList<Vehicle> filterList = new ArrayList<>();
        setUpDagger(application);
        for(Vehicle c: listVehicle){
            if(c.getBrand().toLowerCase().contains(filter.getBrand())
                    || (filter.is_new() && c.getCondition().toLowerCase().contains("new"))
                    || (filter.isUsed() && c.getCondition().toLowerCase().contains("used"))
                    || (filter.isUsd() && c.getCurrency().toLowerCase().contains("usd"))
                    || (filter.is$() && c.getCurrency().toLowerCase().contains("$"))
                    || (filter.getPrice() != null && c.getPrice() == filter.getPrice())){
                filterList.add(c);
            }
        }
        cardViewAdapter.updateListView(listVehicle);
    }

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
