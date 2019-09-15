package com.example.thecanon001.webir.injection;

import android.content.Context;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.Car;
import com.example.thecanon001.webir.model.ContextProvider;

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
        String BASE_URL = ContextProvider.getInstance().getContext().getSharedPreferences("config", Context.MODE_PRIVATE).getString("url","localhost");
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
    ArrayList<Car> provideLoaderBase4List(){
        ArrayList<Car> carList = new ArrayList<>();
        Car car = new Car();
        car.setModel("Fiat 1");
        car.setKm("10000Km");
        car.setLocation("Montevideo");
        car.setPrice("6500$");
        carList.add(car);

        car = new Car();
        car.setModel("Peugeot 206");
        car.setKm("56777Km");
        car.setLocation("Montevideo");
        car.setPrice("9500$");
        carList.add(car);

        car = new Car();
        car.setModel("Ford Focus");
        car.setKm("8777Km");
        car.setLocation("Canelones");
        car.setPrice("10500$");
        carList.add(car);

        car = new Car();
        car.setModel("Renault Clio");
        car.setKm("40000Km");
        car.setLocation("Artigas");
        car.setPrice("11500$");
        carList.add(car);

        car = new Car();
        car.setModel("Mazda MX5");
        car.setKm("124056Km");
        car.setLocation("Salto");
        car.setPrice("12400$");
        carList.add(car);

        car = new Car();
        car.setModel("Fiat Punto");
        car.setKm("76500Km");
        car.setLocation("Montevideo");
        car.setPrice("14400$");
        carList.add(car);

        car = new Car();
        car.setModel("Suzuki Celerio");
        car.setKm("35000Km");
        car.setLocation("Las Piedras");
        car.setPrice("7500$");
        carList.add(car);

        car = new Car();
        car.setModel("Citroen Saxo");
        car.setKm("76777Km");
        car.setLocation("Montevideo");
        car.setPrice("6500$");
        carList.add(car);

        car = new Car();
        car.setModel("Toyota Prius");
        car.setKm("15777Km");
        car.setLocation("Montevideo");
        car.setPrice("29500$");
        carList.add(car);

        car = new Car();
        car.setModel("Rey Joaking");
        car.setKm("0km");
        car.setLocation("A 2 cuadras de la fing");
        car.setPrice("precio incalculable");
        carList.add(car);

        car = new Car();
        car.setModel("Jill Smith");
        car.setKm("Disfruta de coquetear");
        car.setLocation("cada hombre");
        car.setPrice("que encuentra");
        carList.add(car);

        return carList;
    }

    private ArrayList<String> generateImageList() {
        ArrayList<String> list = new ArrayList<>();

        return list;
    }
}
