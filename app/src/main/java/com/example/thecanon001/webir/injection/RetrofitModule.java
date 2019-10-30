package com.example.thecanon001.webir.injection;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.Vehicle;
import com.example.thecanon001.webir.model.ConfigProvider;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {


    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        String BASE_URL = ConfigProvider.getInstance().getUrl(ConfigProvider.getInstance().getUrl_type());
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    WebServiceClient provideWebServiceClient(Retrofit retrofit){
       return  retrofit.create(WebServiceClient.class);
    }

    @Singleton
    @Provides
    ArrayList<Vehicle> provideLoaderBase4List(){
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Fiat");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Fiat 1 perfecto estado");
        vehicle.setPrice(3700);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Peugeot");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Peugeot 206");
        vehicle.setPrice(5500);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Ford");
        vehicle.setCondition("new");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Ford Focus");
        vehicle.setPrice(6500);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Renault");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Renault Clio");
        vehicle.setPrice(4566);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Mazda");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Mazda MX5");
        vehicle.setPrice(6700);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Fiat");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Fiat Punto");
        vehicle.setPrice(9500);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Suzuki");
        vehicle.setCondition("new");
        vehicle.setCurrency("$");
        vehicle.setTitle("Suzuki Celerio");
        vehicle.setPrice(45000);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Citroen");
        vehicle.setCondition("used");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Citroen Saxo");
        vehicle.setPrice(7500);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setCondition("new");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Toyota Prius");
        vehicle.setPrice(12700);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Joaking");
        vehicle.setCondition("new");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Gominola");
        vehicle.setPrice(999999);
        vehicleList.add(vehicle);

        vehicle = new Vehicle();
        vehicle.setBrand("Jill");
        vehicle.setCondition(";)");
        vehicle.setCurrency("USD");
        vehicle.setTitle("Potra indomable");
        vehicle.setPrice(1);
        vehicleList.add(vehicle);

        return vehicleList;
    }

}
