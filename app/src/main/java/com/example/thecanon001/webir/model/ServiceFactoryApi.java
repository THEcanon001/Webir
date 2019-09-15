package com.example.thecanon001.webir.model;

public class ServiceFactoryApi implements ServiceFactory {
    @Override
    public CarService getCarService() {
        return new CarServiceApi();
    }
}
