package com.example.thecanon001.webir.model;

import android.app.Application;

import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.entity.Car;
import com.example.thecanon001.webir.injection.BaseAplicattion;

import java.util.ArrayList;

import javax.inject.Inject;

public class CarServiceStub implements CarService {

    @Inject
    ArrayList<Car> listCar;

    @Override
    public ArrayList<Car> getCarList(Application application) {
        setUpDagger(application);
        listCar.get(0).setImage(application.getString(R.string.image0));
        listCar.get(1).setImage(application.getString(R.string.image1));
        listCar.get(2).setImage(application.getString(R.string.image2));
        listCar.get(3).setImage(application.getString(R.string.image3));
        listCar.get(4).setImage(application.getString(R.string.image4));
        listCar.get(5).setImage(application.getString(R.string.image5));
        listCar.get(6).setImage(application.getString(R.string.image6));
        listCar.get(7).setImage(application.getString(R.string.image7));
        listCar.get(8).setImage(application.getString(R.string.image8));
        listCar.get(9).setImage(application.getString(R.string.image9));
        listCar.get(10).setImage(application.getString(R.string.image10));
        return listCar;
    }

    @Override
    public ArrayList<Car> getCarList(Application application, String filter) {
        ArrayList<Car> filterList = new ArrayList<>();
        setUpDagger(application);
        for(Car c: listCar){
            if(c.getModel().toLowerCase().contains(filter.toLowerCase()) || c.getKm().toLowerCase().contains(filter.toLowerCase())
                    ||c.getPrice().toLowerCase().contains(filter.toLowerCase())
                    ||c.getLocation().toLowerCase().contains(filter.toLowerCase())){
                filterList.add(c);
            }
        }
        return filterList;
    }

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
