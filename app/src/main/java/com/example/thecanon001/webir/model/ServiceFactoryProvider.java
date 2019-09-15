package com.example.thecanon001.webir.model;


public final class ServiceFactoryProvider {

    private ServiceFactoryProvider(){ }

    public static ServiceFactory getServiceFactory(boolean stub){
       if (!stub) {
            return new ServiceFactoryApi();
        }
        else{
           return new ServiceFactoryStub();
       }
    }

}
