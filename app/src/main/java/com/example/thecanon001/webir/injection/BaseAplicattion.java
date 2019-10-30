package com.example.thecanon001.webir.injection;

import android.app.Application;

public class BaseAplicattion extends Application {
   private RetrofitComponent retrofitComponent;

   @Override
    public void onCreate(){
       super.onCreate();
       retrofitComponent = DaggerRetrofitComponent.builder()
               .retrofitModule(new RetrofitModule())
               .build();
   }

   public RetrofitComponent getRetrofitComponent(){
       return retrofitComponent;
   }

   public void refresh(){
       this.onCreate();
   }
}
