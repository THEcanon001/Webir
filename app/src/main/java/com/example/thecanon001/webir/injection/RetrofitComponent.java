package com.example.thecanon001.webir.injection;

import com.example.thecanon001.webir.model.CarServiceApi;
import com.example.thecanon001.webir.model.CarServiceStub;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    //connect module whit class
    void inject(CarServiceApi carServiceApi);

    void inject(CarServiceStub carServiceStub);
}
