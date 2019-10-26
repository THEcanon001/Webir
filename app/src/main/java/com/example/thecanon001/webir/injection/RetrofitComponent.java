package com.example.thecanon001.webir.injection;

import com.example.thecanon001.webir.model.CarServiceApi;
import com.example.thecanon001.webir.model.CarServiceStub;
import com.example.thecanon001.webir.model.UserServiceApi;
import com.example.thecanon001.webir.model.UserServiceStub;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    //connect module whit class
    void inject(CarServiceApi carServiceApi);

    void inject(CarServiceStub carServiceStub);

    void inject(UserServiceApi userServiceApi);

    void inject(UserServiceStub userServiceStub);
}
