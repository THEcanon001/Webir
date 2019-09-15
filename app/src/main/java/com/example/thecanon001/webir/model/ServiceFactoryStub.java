package com.example.thecanon001.webir.model;

public class ServiceFactoryStub implements ServiceFactory {
    @Override
    public CarService getCarService() {
        return new CarServiceStub();
    }
}
